package cn.edu.whut.springbear.course.service.vod.controller.feign;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.service.vod.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-29 07:11
 */
@RestController
@RequestMapping("feign/vod/course")
public class CourseFeignController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("查询课程（模糊查询）")
    @GetMapping("list/{courseName}")
    public List<Course> listCoursesByName(@ApiParam(name = "courseName", value = "课程名称", required = true) @PathVariable String courseName) {
        return courseService.listCoursesByName(courseName);
    }
}
