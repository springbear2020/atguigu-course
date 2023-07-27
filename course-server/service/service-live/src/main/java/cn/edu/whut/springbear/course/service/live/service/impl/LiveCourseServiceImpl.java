package cn.edu.whut.springbear.course.service.live.service.impl;

import cn.edu.whut.springbear.course.client.vod.TeacherFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseAccount;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseDescription;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseFormVo;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseVo;
import cn.edu.whut.springbear.course.common.util.DateUtils;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.live.mapper.LiveCourseMapper;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseAccountService;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseDescriptionService;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseService;
import cn.edu.whut.springbear.course.service.live.util.MTCloud;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 直播课程表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@Service
public class LiveCourseServiceImpl extends ServiceImpl<LiveCourseMapper, LiveCourse> implements LiveCourseService {
    @Autowired
    private TeacherFeignClient teacherFeignClient;
    @Autowired
    private MTCloud mtCloud;
    @Autowired
    private LiveCourseDescriptionService liveCourseDescriptionService;
    @Autowired
    private LiveCourseAccountService liveCourseAccountService;

    @Override
    public Page<LiveCourse> getPageData(Long current, Long size) {
        // 查询直播课程分页数据
        Page<LiveCourse> page = new Page<>(current, size);
        baseMapper.selectPage(page, null);

        // 遍历每一条直播课程数据，查询讲师信息
        List<LiveCourse> records = page.getRecords();
        for (LiveCourse record : records) {
            Teacher teacher = teacherFeignClient.getTeacherLive(record.getTeacherId());
            record.getParam().put("teacherName", teacher.getName());
            record.getParam().put("teacherLevel", teacher.getLevel());
        }
        return page;
    }

    @Override
    public boolean saveLiveCourse(LiveCourseFormVo liveCourseFormVo) {
        // 拷贝前端传输的的直播课程参数
        LiveCourse liveCourse = new LiveCourse();
        BeanUtils.copyProperties(liveCourseFormVo, liveCourse);
        // 查询直播课程教师信息
        Teacher teacher = teacherFeignClient.getTeacherLive(liveCourseFormVo.getTeacherId());

        HashMap<Object, Object> options = new HashMap<>();
        // 直播类型：[1]教育直播（默认） [2] 生活直播
        options.put("scenes", 2);
        options.put("password", liveCourseFormVo.getPassword());
        // 在欢拓云平台创建直播课程信息
        String res;
        try {
            res = mtCloud.courseAdd(liveCourse.getCourseName(), teacher.getId().toString(), DateUtils.parseDatetime(liveCourse.getStartTime()), DateUtils.parseDatetime(liveCourse.getEndTime()), teacher.getName(), teacher.getIntro(), options);
        } catch (Exception e) {
            throw new CourseException(30000, e.getMessage());
        }

        // 解析欢拓云平台响应的 json 数据
        JSONObject data = JSON.parseObject(res);
        Integer code = data.getInteger("code");
        String msg = data.getString("msg");
        data = data.getObject("data", JSONObject.class);
        if (code != MTCloud.CODE_SUCCESS) {
            throw new CourseException(30000, msg);
        }

        // 保存直播课程信息
        liveCourse.setCourseId(data.getLong("course_id"));
        baseMapper.insert(liveCourse);
        // 保存课程详情信息
        LiveCourseDescription liveCourseDescription = new LiveCourseDescription();
        liveCourseDescription.setDescription(liveCourseFormVo.getDescription());
        liveCourseDescription.setLiveCourseId(liveCourse.getId());
        liveCourseDescriptionService.save(liveCourseDescription);
        // 保存课程账号信息
        LiveCourseAccount liveCourseAccount = new LiveCourseAccount();
        liveCourseAccount.setLiveCourseId(liveCourse.getId());
        liveCourseAccount.setLiveAccount(data.getString("bid"));
        liveCourseAccount.setLivePassword(liveCourseFormVo.getPassword());
        liveCourseAccount.setAdminKey(data.getString("admin_key"));
        liveCourseAccount.setUserKey(data.getString("user_key"));
        liveCourseAccount.setLiveKey(data.getString("zhubo_key"));
        liveCourseAccountService.save(liveCourseAccount);
        return true;
    }

    @Override
    public boolean removeLiveCourse(Long id) {
        // 根据 id 查询直播课程信息
        LiveCourse liveCourse = baseMapper.selectById(id);
        if (liveCourse == null) {
            throw new CourseException(30000, "直播课程信息不存在");
        }
        try {
            // 调用方法删除欢拓云平台直播课程
            mtCloud.courseDelete(liveCourse.getCourseId().toString());
        } catch (Exception e) {
            throw new CourseException(30000, e.getMessage());
        }
        // 删除直播课程表数据、课程详情信息、课程账号信息
        return baseMapper.deleteById(id) == 1 && liveCourseDescriptionService.removeByCourseId(id) && liveCourseAccountService.removeByCourseId(id);
    }

    @Override
    public LiveCourseFormVo getLiveCourseFormVo(Long id) {
        // 查询课程信息
        LiveCourse liveCourse = this.getById(id);
        // 根据课程 ID 查询课程详情信息
        LiveCourseDescription liveCourseDescription = liveCourseDescriptionService.getByLiveCourseId(id);
        // 对象属性拷贝
        LiveCourseFormVo liveCourseFormVo = new LiveCourseFormVo();
        BeanUtils.copyProperties(liveCourse, liveCourseFormVo);
        liveCourseFormVo.setDescription(liveCourseDescription.getDescription());
        return liveCourseFormVo;
    }

    @Override
    public boolean updateLiveCourse(LiveCourseFormVo liveCourseFormVo) {
        // 根据 id 获取直播课程基本信息
        LiveCourse liveCourse = baseMapper.selectById(liveCourseFormVo.getId());
        BeanUtils.copyProperties(liveCourseFormVo, liveCourse);
        // 查询讲师信息
        Teacher teacher = teacherFeignClient.getTeacherLive(liveCourseFormVo.getTeacherId());

        String res;
        try {
            // 请求欢拓云平台更新直播课程信息
            res = mtCloud.courseUpdate(liveCourse.getCourseId().toString(), teacher.getId().toString(), liveCourse.getCourseName(), DateUtils.parseDatetime(liveCourse.getStartTime()), DateUtils.parseDatetime(liveCourse.getEndTime()), teacher.getName(), teacher.getIntro(), new HashMap<>());
        } catch (Exception e) {
            throw new CourseException(30000, e.getMessage());
        }

        // 解析欢拓云平台响应的 json 数据
        JSONObject data = JSON.parseObject(res);
        Integer code = data.getInteger("code");
        String msg = data.getString("msg");
        data = data.getObject("data", JSONObject.class);
        if (code != MTCloud.CODE_SUCCESS) {
            throw new CourseException(30000, msg);
        }

        // 更新直播课程基本信息
        liveCourse.setCourseId(data.getLong("course_id"));
        baseMapper.updateById(liveCourse);
        // 直播课程描述信息更新
        LiveCourseDescription liveCourseDescription = liveCourseDescriptionService.getByLiveCourseId(liveCourse.getId());
        liveCourseDescription.setDescription(liveCourseFormVo.getDescription());
        liveCourseDescriptionService.updateById(liveCourseDescription);
        return true;
    }

    @Override
    public List<LiveCourseVo> getLatelyList() {
        // 获取最近的 5 条课程直播信息
        List<LiveCourseVo> liveCourseVoList = baseMapper.findLatelyList();
        for (LiveCourseVo liveCourseVo : liveCourseVoList) {
            liveCourseVo.setStartTimeString(DateUtils.parseDatetime(liveCourseVo.getStartTime()));
            liveCourseVo.setEndTimeString(DateUtils.parseDatetime(liveCourseVo.getEndTime()));
            Long teacherId = liveCourseVo.getTeacherId();
            Teacher teacher = teacherFeignClient.getTeacherLive(teacherId);
            liveCourseVo.setTeacher(teacher);
            liveCourseVo.setLiveStatus(this.liveStatus(liveCourseVo));
        }
        return null;
    }

    /**
     * 获取直播课程状态：[0]:未开始 [1]直播中 [2]直播结束
     *
     * @param liveCourse 直播课程
     * @return 直播课程状态
     */
    private int liveStatus(LiveCourse liveCourse) {
        // 直播状态：[0]:未开始 [1]直播中 [2]直播结束
        int liveStatus;
        Date curTime = new Date();
        if (DateUtils.dateCompare(curTime, liveCourse.getStartTime())) {
            liveStatus = 0;
        } else if (DateUtils.dateCompare(curTime, liveCourse.getEndTime())) {
            liveStatus = 1;
        } else {
            liveStatus = 2;
        }
        return liveStatus;
    }
}
