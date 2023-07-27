package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.model.pojo.vod.Video;
import cn.edu.whut.springbear.course.service.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22·
 */
@RestController
@Api(tags = "小节管理接口")
@RequestMapping(value = "/admin/vod/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "查询小节数据")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.success("查询小节数据成功", video);
    }

    @ApiOperation(value = "添加小节")
    @PostMapping("save")
    public Result save(@RequestBody Video video) {
        return videoService.save(video) ? Result.success("添加小节成功", video) : Result.fail("添加小节失败", video);
    }

    @ApiOperation(value = "修改小节")
    @PutMapping("update")
    public Result updateById(@RequestBody Video video) {
        return videoService.updateById(video) ? Result.success("修改小节成功", null) : Result.fail("修改小节失败", null);
    }

    @ApiOperation(value = "删除小节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return videoService.deleteVideoById(id) ? Result.success("删除小节成功", null) : Result.fail("删除小节失败", null);
    }
}

