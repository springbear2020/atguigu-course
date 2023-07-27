package cn.edu.whut.springbear.course.service.activity.service;

import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponUse;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponInfoVo;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
public interface CouponInfoService extends IService<CouponInfo> {

    /**
     * 查询已使用的优惠券分页数据
     *
     * @param curNum           当前页码
     * @param pageSize         每页记录数
     * @param couponUseQueryVo 额外查询条件
     * @return 分页数据
     */
    Page<CouponUse> getUsedPageData(Long curNum, Long pageSize, CouponUseQueryVo couponUseQueryVo);

    /**
     * 给用户发放优惠券
     *
     * @param userId 用户 ID
     * @return true: 发放成功
     */
    boolean userObtainCoupon(Long userId);

    /**
     * 查询用户名下所有的优惠券
     *
     * @param userId 用户 ID
     * @return 用户优惠券列表
     */
    List<CouponInfoVo> listUserCoupons(Long userId);
}
