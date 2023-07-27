package cn.edu.whut.springbear.course.service.live.service;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseFormVo;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 直播课程表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseService extends IService<LiveCourse> {
    /**
     * 查询直播课程分页数据
     *
     * @param current 当前页码
     * @param size    每页显示数量
     * @return 直播课程分页数据
     */
    Page<LiveCourse> getPageData(Long current, Long size);

    /**
     * 保存直播课程
     *
     * @param liveCourseVo 直播课程信息
     * @return true：保存成功
     */
    boolean saveLiveCourse(LiveCourseFormVo liveCourseVo);

    /**
     * 删除直播课程
     *
     * @param id 直播课程 ID
     * @return true：删除成功
     */
    boolean removeLiveCourse(Long id);

    /**
     * 查询直播课程
     *
     * @param id 直播课程 ID
     * @return 直播课程
     */
    LiveCourseFormVo getLiveCourseFormVo(Long id);

    /**
     * 更新直播课程
     *
     * @param liveCourseVo 新的直播课程信息
     * @return true：更新成功
     */
    boolean updateLiveCourse(LiveCourseFormVo liveCourseVo);

    /**
     * 获取最近的直播课程信息
     *
     * @return 直播课程信息
     */
    List<LiveCourseVo> getLatelyList();

    /**
     * 从欢拓云平台获取直播课程的访问 token
     *
     * @param courseId 课程 ID
     * @return null：获取失败
     */
    JSONObject getPlayAuth(Long courseId);
}
