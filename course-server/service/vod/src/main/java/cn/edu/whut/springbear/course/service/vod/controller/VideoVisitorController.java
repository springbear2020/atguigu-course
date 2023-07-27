package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.model.vo.vod.VideoVisitorCountVo;
import cn.edu.whut.springbear.course.service.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-23
 */
@RestController
@Api( tags = "课程访客管理")
@RequestMapping("/admin/vod/video/visitor")
public class VideoVisitorController {
    @Autowired
    private VideoVisitorService videoVisitorService;

    @ApiOperation("统计课程观看人数")
    @GetMapping("count/{courseId}/{startDate}/{endDate}")
    public Result countVisitors(
            @ApiParam(name = "课程 ID", value = "courseId") @PathVariable Long courseId,
            @ApiParam(name = "开始时间", value = "startDate") @PathVariable String startDate,
            @ApiParam(name = "结束时间", value = "endDate") @PathVariable String endDate) {
        List<VideoVisitorCountVo> videoVisitorCountVos = videoVisitorService.countVisitors(courseId, startDate, endDate);
        return videoVisitorCountVos.isEmpty() ? Result.success("暂无课程访客数据", null) : Result.success("统计课程访客成功", videoVisitorCountVos);
    }
}

