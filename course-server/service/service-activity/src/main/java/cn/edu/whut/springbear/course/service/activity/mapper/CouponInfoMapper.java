package cn.edu.whut.springbear.course.service.activity.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Repository
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    /**
     * 查询用户名下所有优惠券
     *
     * @param userId 用户 ID
     * @return 用户优惠券列表
     */
    @Select("select o.id,\n" +
            "       e.id                         couponUseId,\n" +
            "       e.coupon_status              available,\n" +
            "       o.coupon_name                name,\n" +
            "       UNIX_TIMESTAMP(o.start_time) startAt,\n" +
            "       UNIX_TIMESTAMP(o.end_time)   endAt,\n" +
            "       o.rule_desc                  description,\n" +
            "       o.amount                     value,\n" +
            "       o.amount                     valueDesc\n" +
            "from coupon_info o,\n" +
            "     coupon_use e\n" +
            "where o.id = e.coupon_id\n" +
            "  and o.id in (select coupon_id from coupon_use where user_id = #{userId})")
    List<CouponInfoVo> listUserCoupons(@Param("userId") Long userId);
}
