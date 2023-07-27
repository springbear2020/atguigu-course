package cn.edu.whut.springbear.course.service.vod.controller.api;

import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 09:27
 */
@RestController
@RequestMapping("api/vod")
public class VodApiController {
    @Autowired
    private VodService vodService;

    @GetMapping("get/{videoId}")
    public Result getCourseVideoAuth(@PathVariable Long videoId) {
        Map<String, String> videoAuth = vodService.getVideoAuth(videoId);
        return videoAuth == null ? Result.fail("查询视频信息失败", null) : Result.success("查询视频信息成功", videoAuth);
    }
}
