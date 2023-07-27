package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.common.model.pojo.vod.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
public interface CourseDescriptionService extends IService<CourseDescription> {
    /**
     * 删除课程对应的课程描述信息
     *
     * @param courseId 课程 ID
     */
    void deleteDescriptionOfCourse(Long courseId);
}
