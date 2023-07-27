package cn.edu.whut.springbear.course.service.vod.controller.api;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.common.model.vo.vod.CourseQueryVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-29 08:37
 */
@RestController
@RequestMapping("api/vod/course")
public class CourseApiController {
    @Autowired
    private CourseService courseService;

    @GetMapping("page/{pageNum}/{pageSize}/{subjectParentId}")
    public Result getCoursePageData(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Long subjectParentId) {
        CourseQueryVo courseQueryVo = new CourseQueryVo();
        courseQueryVo.setSubjectParentId(subjectParentId);
        Page<Course> coursePage = courseService.listCoursePageData(pageNum, pageSize, courseQueryVo);
        return coursePage.getRecords().isEmpty() ? Result.fail("查询课程分页数据失败", null) : Result.success("查询课程分页数据成功", coursePage);
    }

    @GetMapping("get")
    public Result getCourseDetails(@RequestParam Long id) {
        Course courseDetails = courseService.getCourse(id);
        return courseDetails == null ? Result.fail("查询课程信息失败", null) : Result.success("查询课程信息成功", courseDetails);
    }
}
