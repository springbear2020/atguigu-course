package cn.edu.whut.springbear.course.service.live.controller.admin;


import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseAccount;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 直播课程账号表（受保护信息） 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@RestController
@Api(tags = "直播课程账号信息管理")
@RequestMapping("/admin/live/course/account")
public class LiveAccountController {
    @Autowired
    private LiveCourseAccountService liveCourseAccountService;

    @ApiOperation("查询直播课程账号信息")
    @GetMapping("get/{id}")
    public Result getLiveCourseAccount(@PathVariable Long id) {
        LiveCourseAccount liveCourseAccount = liveCourseAccountService.getByLiveCourseId(id);
        return liveCourseAccount == null ? Result.fail("查询直播课程账号信息失败", null) : Result.success("查询直播课程账号信息成功", liveCourseAccount);
    }
}

