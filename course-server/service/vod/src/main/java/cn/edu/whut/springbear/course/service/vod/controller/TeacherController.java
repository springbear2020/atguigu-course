package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.model.vo.vod.TeacherQueryVo;
import cn.edu.whut.springbear.course.service.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 21:26:26
 */
@Api(tags = "讲师管理接口")
@RestController
@CrossOrigin
@RequestMapping("admin/vod/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("添加讲师")
    @PostMapping("save")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher) ? Result.success("添加讲师成功", null) : Result.fail("添加讲师失败", null);
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("remove/{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "讲师 ID", required = true) @PathVariable("id") Integer id) {
        return teacherService.removeById(id) ? Result.success("删除讲师成功", null) : Result.fail("删除讲师失败", null);
    }

    @ApiOperation("删除讲师（批量）")
    @DeleteMapping("remove")
    public Result deleteTeachersBatch(@RequestBody List<Long> idList) {
        return teacherService.removeByIds(idList) ? Result.success("批量删除讲师成功", null) : Result.fail("批量删除讲师失败", null);
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public Result updateTeacherById(@RequestBody Teacher teacher) {
        return teacherService.updateById(teacher) ? Result.success("更新讲师成功", null) : Result.fail("更新讲师失败", null);
    }

    @ApiOperation("查询讲师（条件分页）")
    @GetMapping("page/{pageNum}/{pageSize}")
    public Result getTeachersPageData(
            @ApiParam(name = "pageNum", value = "当前页码", required = true) @PathVariable Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页显示数量", required = true) @PathVariable Integer pageSize,
            TeacherQueryVo teacherQueryVo) {
        return Result.success("查询讲师分页数据成功", teacherService.listPageData(pageNum, pageSize, teacherQueryVo));
    }

    @ApiOperation("查询讲师")
    @GetMapping("get/{id}")
    public Result getTeacherById(@ApiParam(name = "id", value = "讲师 ID", required = true) @PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getById(id);
        return Result.success("查询讲师成功", teacher);
    }

    @ApiOperation("查询所有讲师")
    @GetMapping("list")
    public Result getAllTeachers() {
        List<Teacher> teachers = teacherService.list();
        return Result.success("查询所有讲师成功", teachers);
    }
}

