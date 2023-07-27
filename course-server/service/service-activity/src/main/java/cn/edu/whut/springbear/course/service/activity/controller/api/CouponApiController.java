package cn.edu.whut.springbear.course.service.activity.controller.api;

import cn.edu.whut.springbear.course.common.model.vo.activity.CouponInfoVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.common.util.interceptor.AuthContextHolder;
import cn.edu.whut.springbear.course.service.activity.service.CouponInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 15:26
 */
@RestController
@RequestMapping("api/activity/coupon")
public class CouponApiController {
    @Value("${coupon.exchangeCode}")
    private String couponCode;
    @Autowired
    private CouponInfoService couponInfoService;

    @GetMapping("exchange")
    public Result exchangeCoupon(@RequestParam String code) {
        if (!couponCode.equals(code)) {
            return Result.fail("兑换优惠券失败，兑换码不存在", null);
        }

        Long userId = AuthContextHolder.getUserId();
        // 给用户发放优惠券
        if (!couponInfoService.userObtainCoupon(userId)) {
            return Result.fail("兑换优惠券失败，系统暂无可用优惠券", null);
        }
        return Result.success("兑换优惠券成功", null);
    }

    @GetMapping("list")
    public Result listUserCoupons() {
        Long userId = AuthContextHolder.getUserId();
        // 查询用户名下所有可用的优惠券
        List<CouponInfoVo> userCoupons = couponInfoService.listUserCoupons(userId);

        // 将所有的优惠券分为可用和不可用两类
        Map<String, List<CouponInfoVo>> res = new HashMap<>();
        List<CouponInfoVo> availableCoupons = new ArrayList<>();
        List<CouponInfoVo> disabledCoupons = new ArrayList<>();
        for (CouponInfoVo userCoupon : userCoupons) {
            userCoupon.setUnitDesc("元");
            userCoupon.setCondition("无门槛券");
            // 将优惠券价格从元换为分
            String value = String.valueOf(Double.parseDouble(userCoupon.getValue()) * 100);
            userCoupon.setValue(value);
            // [1]未使用；[2]已使用
            Integer available = userCoupon.getAvailable();
            if (available == 1) {
                availableCoupons.add(userCoupon);
            } else {
                userCoupon.setReason("优惠券已过期");
                disabledCoupons.add(userCoupon);
            }
        }

        res.put("availableCoupons", availableCoupons);
        res.put("disabledCoupons", disabledCoupons);
        return Result.success("查询用户优惠券信息成功", res);
    }
}
