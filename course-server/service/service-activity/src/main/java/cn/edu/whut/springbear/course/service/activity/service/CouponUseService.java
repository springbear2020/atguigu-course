package cn.edu.whut.springbear.course.service.activity.service;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponUse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券领用表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
public interface CouponUseService extends IService<CouponUse> {
    /**
     * 更新优惠券使用状态
     *
     * @param couponUseId 优惠券状态 ID，对应一张优惠券
     * @param orderId     订单 ID
     * @return true：更新成功
     */
    Boolean updateCouponUseStatus(Long couponUseId, Long orderId);
}
