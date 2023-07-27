package cn.edu.whut.springbear.course.service.live.service;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseDescriptionService extends IService<LiveCourseDescription> {
    /**
     * 通过直播课程 ID 删除直播课程描述信息
     *
     * @param liveCourseId 直播课程 ID
     * @return true：删除成功
     */
    boolean removeByCourseId(Long liveCourseId);

    /**
     * 通过课程 ID 查询课程详情信息
     *
     * @param courseId 课程 ID
     * @return 课程详情信息
     */
    LiveCourseDescription getByLiveCourseId(Long courseId);
}
