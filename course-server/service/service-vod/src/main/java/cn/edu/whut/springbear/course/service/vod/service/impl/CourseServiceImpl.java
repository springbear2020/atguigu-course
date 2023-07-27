package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.client.order.OrderFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Chapter;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.common.model.pojo.vod.CourseDescription;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.common.model.vo.vod.CourseFormVo;
import cn.edu.whut.springbear.course.common.model.vo.vod.CourseQueryVo;
import cn.edu.whut.springbear.course.common.util.NumberUtils;
import cn.edu.whut.springbear.course.common.util.interceptor.AuthContextHolder;
import cn.edu.whut.springbear.course.service.vod.mapper.CourseDescriptionMapper;
import cn.edu.whut.springbear.course.service.vod.mapper.CourseMapper;
import cn.edu.whut.springbear.course.service.vod.mapper.SubjectMapper;
import cn.edu.whut.springbear.course.service.vod.mapper.TeacherMapper;
import cn.edu.whut.springbear.course.service.vod.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Autowired
    private OrderFeignClient orderFeignClient;


    @Override
    public Page<Course> listCoursePageData(int pageNum, int pageSize, CourseQueryVo courseQueryVo) {
        // 拼接查询条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQueryVo.getTitle())) {
            wrapper.like("title", courseQueryVo.getTitle());
        }
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectId())) {
            wrapper.eq("subject_id", courseQueryVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseQueryVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseQueryVo.getTeacherId())) {
            wrapper.eq("teacher_id", courseQueryVo.getTeacherId());
        }

        // 获取课程分页数据
        Page<Course> page = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(page, wrapper);

        /*
         * 遍历每一条课程信息，根据讲师 ID、一级分类 ID、二级分类 ID 查询其对应的名称
         */
        List<Course> records = page.getRecords();
        records.forEach(item -> {
            item.setTeacherName(teacherMapper.getTeacherNameById(item.getTeacherId()));
            item.setParentSubjectName(subjectMapper.getSubjectNameById(item.getSubjectParentId()));
            item.setSubjectName(subjectMapper.getSubjectNameById(item.getSubjectId()));
        });

        page.setRecords(records);
        return page;
    }

    @Override
    public Long saveCourse(CourseFormVo courseFormVo) {
        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);

        // 保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public Course getCourseDetails(Long courseId) {
        Course course = this.getById(courseId);
        // 获取课程教师信息
        Teacher teacher = teacherService.getById(course.getTeacherId());
        course.setTeacherName(teacher.getName());
        course.setTeacherAvatar(teacher.getAvatar());
        // 获取课程分类信息：一级分类和二级分类
        course.setParentSubjectName(subjectMapper.getSubjectNameById(course.getSubjectParentId()));
        course.setSubjectName(subjectMapper.getSubjectNameById(course.getSubjectId()));
        // 获取课程描述信息
        course.setDescription(courseDescriptionMapper.getDescriptionByCourseId(courseId));
        return course;
    }

    @Override
    public boolean updateCourse(CourseFormVo courseFormVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        // 更新课程基本信息和课程描述信息
        return baseMapper.updateById(course) + courseDescriptionMapper.updateDescriptionByCourseId(course.getDescription(), course.getId()) == 2;
    }

    @Override
    public boolean updateCourseStatus(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return baseMapper.updateById(course) == 1;
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        // 删除课程小节
        videoService.deleteVideosOfCourse(courseId);
        // 删除课程章节
        chapterService.deleteChaptersOfCourse(courseId);
        // 删除课程描述
        courseDescriptionService.deleteDescriptionOfCourse(courseId);
        // 删除课程
        return baseMapper.deleteById(courseId) == 1;
    }

    @Override
    public List<Course> listCoursesByName(String courseName) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", courseName);
        // 查询每门课程的描述信息
        List<Course> courses = baseMapper.selectList(queryWrapper);
        for (Course course : courses) {
            String description = courseDescriptionMapper.getDescriptionByCourseId(course.getId());
            course.setDescription(description);
        }
        return courses;
    }

    @Override
    public Course getCourse(Long courseId) {
        Course course = this.getCourseDetails(courseId);
        // 更新课程访问量
        int views = NumberUtils.randomNumber(50);
        course.setViewCount(course.getViewCount() + views);
        baseMapper.updateById(course);

        // 查询当前用户当前课程是否已经购买
        Long userId = AuthContextHolder.getUserId();
        String payStatus = orderFeignClient.getUserCoursePayStatus(userId, courseId);
        course.setIsBuy("PAID".equals(payStatus));
        // 获取课程大纲信息：一个课程包含多个章节，每个章节包含多个小节
        List<Chapter> chapters = chapterService.listChaptersOfCourse(courseId);
        course.setChapters(chapters);
        return course;
    }
}
