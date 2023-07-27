package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.common.model.pojo.vod.Video;
import cn.edu.whut.springbear.course.common.model.vo.vod.VideoVisitorCountVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.VideoService;
import cn.edu.whut.springbear.course.service.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private VideoVisitorService videoVisitorService;

    @ApiOperation(value = "新增小节")
    @PostMapping("save")
    public Result save(@RequestBody Video video) {
        return videoService.save(video) ? Result.success("新增小节成功", video) : Result.fail("新增小节失败", video);
    }

    @ApiOperation(value = "删除小节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return videoService.deleteVideoById(id) ? Result.success("删除小节成功", null) : Result.fail("删除小节失败", null);
    }

    @ApiOperation(value = "修改小节")
    @PutMapping("update")
    public Result updateById(@RequestBody Video video) {
        return videoService.updateById(video) ? Result.success("修改小节成功", null) : Result.fail("修改小节失败", null);
    }

    @ApiOperation(value = "查询小节数据")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return video == null ? Result.fail("查询小节数据失败", null) : Result.success("查询小节数据成功", video);
    }

    @ApiOperation("统计课程观看人数")
    @GetMapping("count/{courseId}/{startDate}/{endDate}")
    public Result countVisitors(
            @ApiParam(name = "课程 ID", value = "courseId") @PathVariable Long courseId,
            @ApiParam(name = "开始时间", value = "startDate") @PathVariable String startDate,
            @ApiParam(name = "结束时间", value = "endDate") @PathVariable String endDate) {
        List<VideoVisitorCountVo> videoVisitorCountVos = videoVisitorService.countVisitors(courseId, startDate, endDate);
        return videoVisitorCountVos.isEmpty() ? Result.success("统计课程访客失败", null) : Result.success("统计课程访客成功", videoVisitorCountVos);
    }
}

