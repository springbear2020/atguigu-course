package cn.edu.whut.springbear.course.service.activity.controller;


import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponUse;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponUseQueryVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Api(tags = "优惠券接口管理")
@RestController
@RequestMapping("/admin/activity/coupon")
public class CouponController {
    @Autowired
    private CouponInfoService couponInfoService;

    @ApiOperation(value = "新增优惠券")
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        return couponInfoService.save(couponInfo) ? Result.success("新增优惠券成功", null) : Result.fail("新增优惠券失败", null);
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        return couponInfoService.removeById(id) ? Result.success("删除优惠券成功", null) : Result.fail("删除优惠券失败", null);
    }

    @ApiOperation(value = "删除优惠券（批量）")
    @DeleteMapping("remove")
    public Result batchRemove(@RequestBody List<String> idList) {
        couponInfoService.removeByIds(idList);
        return Result.success("批量删除优惠券成功", null);
    }

    @ApiOperation(value = "修改优惠券")
    @PutMapping("update")
    public Result updateById(@RequestBody CouponInfo couponInfo) {
        return couponInfoService.updateById(couponInfo) ? Result.success("修改优惠券成功", null) : Result.fail("修改惠券失败", null);
    }

    @ApiOperation(value = "查询优惠券")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.success("查询优惠券成功", couponInfo);
    }

    @ApiOperation(value = "查询优惠券分页数据")
    @GetMapping("page/{curNum}/{pageSize}")
    public Result getPageData(
            @ApiParam(name = "curNum", value = "当前页码", required = true) @PathVariable Long curNum,
            @ApiParam(name = "pageSize", value = "每页记录数", required = true) @PathVariable Long pageSize) {
        Page<CouponInfo> page = new Page<>(curNum, pageSize);
        couponInfoService.page(page);
        return Result.success("查询用获取分页数据成功", page);
    }

    @ApiOperation("查询已使用的优惠券分页数据")
    @GetMapping("page/used/{curNum}/{pageSize}")
    public Result getUsedPageData(
            @ApiParam(name = "curNum", value = "当前页码", required = true) @PathVariable Long curNum,
            @ApiParam(name = "pageSize", value = "每页记录数", required = true) @PathVariable Long pageSize,
            @ApiParam(name = "couponUseVo", value = "额外查询条件") CouponUseQueryVo couponUseQueryVo) {
        Page<CouponUse> page = couponInfoService.getUsedPageData(curNum, pageSize, couponUseQueryVo);
        return Result.success("查询已使用的优惠券分页数据成功", page);
    }
}
