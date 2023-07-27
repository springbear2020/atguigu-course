package cn.edu.whut.springbear.course.service.activity.controller.feign;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.service.activity.service.CouponInfoService;
import cn.edu.whut.springbear.course.service.activity.service.CouponUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:07
 */
@RestController
@RequestMapping("feign/activity/coupon")
public class CouponFeignController {
    @Autowired
    private CouponInfoService couponInfoService;
    @Autowired
    private CouponUseService couponUseService;

    @GetMapping("get/{couponId}")
    public CouponInfo getById(@PathVariable("couponId") Long couponId) {
        return couponInfoService.getById(couponId);
    }

    @GetMapping("update/{couponUseId}/{orderId}")
    public Boolean updateCouponInfoUseStatus(@PathVariable("couponUseId") Long couponUseId, @PathVariable("orderId") Long orderId) {
        return couponUseService.updateCouponUseStatus(couponUseId, orderId);
    }
}
