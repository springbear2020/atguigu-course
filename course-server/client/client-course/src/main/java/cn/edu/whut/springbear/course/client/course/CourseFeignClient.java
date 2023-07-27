package cn.edu.whut.springbear.course.client.course;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-26 11:27
 */
@FeignClient(value = "course-service-vod")
public interface CourseFeignClient {
    /**
     * 根据课程名称查询课程列表（模糊查询）
     *
     * @param title 课程名称
     * @return 课程列表
     */
    @GetMapping("feign/vod/course/list/{title}")
    List<Course> listCoursesByTitle(@PathVariable String title);

    /**
     * 查询课程信息
     *
     * @param courseId 课程 ID
     * @return 课程信息
     */
    @GetMapping("feign/vod/course/get/{courseId}")
    Course getCourse(@PathVariable Long courseId);
}
