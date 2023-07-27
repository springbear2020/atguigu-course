package cn.edu.whut.springbear.course.service.live.controller.api;

import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spring-_-Bear
 * @datetime 2022-11-03 10:09
 */
@RestController
@RequestMapping("/api/live/course")
public class LiveCourseApiController {
    @Autowired
    private LiveCourseService liveCourseService;

    @GetMapping("auth/{courseId}")
    public Result getPlayAuth(@PathVariable Long courseId) {
        JSONObject object = liveCourseService.getPlayAuth(courseId);
        return object == null ? Result.fail("获取直播课程 token 失败", null) : Result.success("获取直播课程 token 成功", object);
    }
}