package cn.edu.whut.springbear.course.client.activity;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:06
 */
@FeignClient(value = "course-service-activity")
public interface CouponFeignClient {
    /**
     * 查询优惠券
     *
     * @param couponId 优惠券 ID
     * @return 优惠券信息
     */
    @GetMapping("feign/activity/coupon/get/{couponId}")
    CouponInfo getCouponInfo(@PathVariable("couponId") Long couponId);

    /**
     * 更新优惠券使用状态
     *
     * @param couponUseId 优惠券状态 ID，对应一张优惠券
     * @param orderId     订单 ID
     * @return true：更新成功
     */
    @GetMapping("feign/activity/coupon/update/{couponUseId}/{orderId}")
    Boolean updateCouponInfoUseStatus(@PathVariable("couponUseId") Long couponUseId, @PathVariable("orderId") Long orderId);
}
