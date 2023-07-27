package cn.edu.whut.springbear.course.service.live.controller;


import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseFormVo;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 直播课程表 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@RestController
@Api(tags = "直播课程管理接口")
@RequestMapping("/admin/live/course")
public class LiveCourseController {
    @Autowired
    private LiveCourseService liveCourseService;

    @ApiOperation(value = "新增直播课程")
    @PostMapping("save")
    public Result save(@RequestBody LiveCourseFormVo liveCourseVo) {
        return liveCourseService.saveLiveCourse(liveCourseVo) ? Result.success("新增直播课程成功", null) : Result.fail("新增直播课程失败", null);
    }

    @ApiOperation("删除直播课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return liveCourseService.removeLiveCourse(id) ? Result.success("删除直播课程成功", null) : Result.fail("删除直播课程失败", null);
    }

    @ApiOperation("更新直播课程")
    @PutMapping("update")
    public Result updateById(@RequestBody LiveCourseFormVo liveCourseVo) {
        return liveCourseService.updateLiveCourse(liveCourseVo) ? Result.success("更新直播课程信息成功", null) : Result.fail("更新直播课程信息失败", null);
    }

    // TODO remove the next method?
//    @ApiOperation("查询直播课程")
//    @GetMapping("get/{id}")
//    public Result get(@PathVariable Long id) {
//        LiveCourse liveCourse = liveCourseService.getById(id);
//        return liveCourse == null ? Result.fail("查询直播课程失败", null) : Result.success("查询直播课程成功", liveCourse);
//    }

    @ApiOperation("查询直播课程")
    @GetMapping("get")
    public Result getCourse(@RequestParam Long id) {
        LiveCourseFormVo liveCourseFormVo = liveCourseService.getLiveCourseFormVo(id);
        return liveCourseFormVo == null ? Result.fail("查询直播课程失败", null) : Result.success("查询直播课程成功", liveCourseFormVo);
    }

    @ApiOperation(value = "查询直播课程分页数据")
    @GetMapping("page/{current}/{size}")
    public Result index(
            @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true) @PathVariable Long size) {
        Page<LiveCourse> pageData = liveCourseService.getPageData(current, size);
        return pageData.getRecords().isEmpty() ? Result.fail("查询直播课程分页数据失败", null) : Result.success("查询直播课程分页数据成功", pageData);
    }

    @ApiOperation("查询最近直播课程信息")
    @GetMapping("list")
    public Result findLatelyList() {
        List<LiveCourseVo> latelyCourseList = liveCourseService.getLatelyList();
        return latelyCourseList.isEmpty() ? Result.fail("查询最近直播课程信息失败", null) : Result.success("查询最近直播课程信息成功", null);
    }

}

