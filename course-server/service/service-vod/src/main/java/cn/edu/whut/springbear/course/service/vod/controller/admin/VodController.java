package cn.edu.whut.springbear.course.service.vod.controller.admin;

import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-23 16:54
 */
@RestController
@Api(tags = "视频点播管理")
@RequestMapping("/admin/vod")
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到腾讯云点播平台
     */
    @ApiOperation("上传视频")
    @PostMapping("upload")
    public Result videoUpload(HttpSession session, @ApiParam(name = "file", value = "文件对象", required = true) @RequestParam("file") MultipartFile file) {
        String realPath = session.getServletContext().getRealPath("/");
        String videoId = vodService.videoUpload(file, realPath);
        return videoId == null ? Result.fail("上传视频失败", null) : Result.success("上传视频成功", videoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("remove/{videoId}")
    public Result deleteVideo(@ApiParam(name = "videoId", value = "视频 ID") @PathVariable String videoId) {
        return vodService.deleteVideo(videoId) ? Result.success("删除视频成功", null) : Result.fail("删除视频失败", null);
    }
}
