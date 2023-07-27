package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.CourseDescription;
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
    void deleteDescriptionOfCourse(Long courseId);
}
