package cn.edu.whut.springbear.course.service.live.controller;


import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseConfigVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 直播课程配置表 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@RestController
@Api(tags = "直播课程配置信息管理")
@RequestMapping("/admin/live/course/config")
public class LiveConfigController {
    @Autowired
    private LiveCourseConfigService liveCourseConfigService;

    @ApiOperation("查询直播课程配置信息")
    @GetMapping("get/{courseId}")
    public Result getCourseConfig(@PathVariable Long courseId) {
        LiveCourseConfigVo courseConfigVo = liveCourseConfigService.getCourseConfigByCourseId(courseId);
        return courseConfigVo == null ? Result.fail("查询直播课程配置信息失败", null) : Result.success("查询直播课程配置信息成功", courseConfigVo);
    }

    @ApiOperation("更新直播课程配置信息")
    @PutMapping("update")
    public Result updateConfig(@RequestBody LiveCourseConfigVo liveCourseConfigVo) {
        return liveCourseConfigService.updateConfig(liveCourseConfigVo) ? Result.success("更新直播课程配置信息成功", null) : Result.fail("更新直播课程配置信息失败", null);
    }
}

