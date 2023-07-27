package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.model.vo.vod.CourseFormVo;
import cn.edu.whut.springbear.course.model.vo.vod.CourseQueryVo;
import cn.edu.whut.springbear.course.service.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@RestController
@Api(tags = "课程管理接口")
@RequestMapping("/admin/vod/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("查询课程分页数据")
    @GetMapping("page/{pageNum}/{pageSize}")
    public Result getCoursePageData(
            @ApiParam(name = "pageNum", value = "当前页码", required = true) @PathVariable Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页显示数量", required = true) @PathVariable Integer pageSize,
            @ApiParam(name = "courseQueryVo", value = "额外查询条件") CourseQueryVo courseQueryVo) {
        Page<Course> coursePage = courseService.listCoursePageData(pageNum, pageSize, courseQueryVo);
        return Result.success("查询课程分页数据成功", coursePage);
    }

    @ApiOperation("保存课程")
    @PostMapping("save")
    public Result saveCourseInfo(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourse(courseFormVo);
        return Result.success("保存课程成功", courseId);
    }

    @ApiOperation("查询课程")
    @GetMapping("get/{id}")
    public Result getCourseById(@ApiParam(name = "courseId", value = "课程 id", required = true) @PathVariable Long id) {
        Course courseDetails = courseService.getCourseDetails(id);
        return Result.success("查询课程成功", courseDetails);
    }

    @ApiOperation("更新课程")
    @PutMapping("update")
    public Result updateCourse(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseFormVo.getId();
        return courseService.updateCourse(courseFormVo) ? Result.success("更新课程成功", courseId) : Result.fail("更新课程失败", courseId);
    }

    @ApiOperation("发布课程")
    @PutMapping("update/{id}")
    public Result publishCourse(@ApiParam(name = "courseId", value = "课程 id", required = true) @PathVariable("id") Long id) {
        return courseService.updateCourseStatus(id) ? Result.success("发布课程成功", null) : Result.fail("发布课程失败", null);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("remove/{id}")
    public Result deleteCourse(@ApiParam(name = "courseId", value = "课程 id", required = true) @PathVariable("id") Long id) {
        return courseService.deleteCourse(id) ? Result.success("删除课程成功", null) : Result.fail("删除课程失败", null);
    }
}

