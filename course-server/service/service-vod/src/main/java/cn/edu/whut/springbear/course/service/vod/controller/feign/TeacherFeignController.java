package cn.edu.whut.springbear.course.service.vod.controller.feign;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.service.vod.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 16:38
 */
@RestController
@RequestMapping("feign/vod/teacher")
public class TeacherFeignController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询讲师数据")
    @GetMapping("get/{id}")
    public Teacher getTeacherLive(@PathVariable Long id) {
        return teacherService.getById(id);
    }
}
