package cn.edu.whut.springbear.course.service.activity.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponUse;
import cn.edu.whut.springbear.course.service.activity.mapper.CouponUseMapper;
import cn.edu.whut.springbear.course.service.activity.service.CouponUseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 优惠券领用表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Service
public class CouponUseServiceImpl extends ServiceImpl<CouponUseMapper, CouponUse> implements CouponUseService {
    @Override
    public Boolean updateCouponUseStatus(Long couponUseId, Long orderId) {
        CouponUse couponUse = new CouponUse();
        couponUse.setId(couponUseId);
        couponUse.setOrderId(orderId);
        // [1]未使用；[2]已使用
        couponUse.setCouponStatus("2");
        couponUse.setUsingTime(new Date());
        couponUse.setUsedTime(new Date());
        return baseMapper.updateById(couponUse) == 1;
    }
}
