package cn.edu.whut.springbear.course.service.vod.controller;

import cn.edu.whut.springbear.course.model.pojo.vod.Subject;
import cn.edu.whut.springbear.course.service.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 16:18
 */
@Api(tags = "分类管理接口")
@RestController
@CrossOrigin
@RequestMapping("admin/vod/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation("查询课程分类列表")
    @GetMapping("list/{parentId}")
    public Result listSubSubjects(@PathVariable("parentId") Long parentId) {
        List<Subject> subjects = subjectService.listSubCourses(parentId);
        return Result.success("查询课程分类列表成功", subjects);
    }

    @ApiOperation("导出课程分类数据")
    @GetMapping("export")
    public void exportData(HttpServletResponse response) {
        subjectService.exportCourseData(response);
    }

    @ApiOperation("导入课程分类数据")
    @PostMapping("import")
    public Result importData(@RequestParam("file") MultipartFile file, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/");
        return subjectService.importCourseData(file, realPath) ? Result.success("导入课程数据成功", null) : Result.fail("导入课程数据失败", null);
    }
}
