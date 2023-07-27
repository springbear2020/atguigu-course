package cn.edu.whut.springbear.course.service.activity.service.impl;

import cn.edu.whut.springbear.course.client.user.UserFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponUse;
import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponInfoVo;
import cn.edu.whut.springbear.course.common.model.vo.activity.CouponUseQueryVo;
import cn.edu.whut.springbear.course.common.util.DateUtils;
import cn.edu.whut.springbear.course.common.util.NumberUtils;
import cn.edu.whut.springbear.course.service.activity.mapper.CouponInfoMapper;
import cn.edu.whut.springbear.course.service.activity.mapper.CouponUseMapper;
import cn.edu.whut.springbear.course.service.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
    @Autowired
    private CouponUseMapper couponUseMapper;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Page<CouponUse> getUsedPageData(Long curNum, Long pageSize, CouponUseQueryVo conditions) {
        // 判断条件值是否为空，不为空则进行条件封装
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(conditions.getCouponId())) {
            wrapper.eq("coupon_id", conditions.getCouponId());
        }
        if (!StringUtils.isEmpty(conditions.getCouponStatus())) {
            wrapper.eq("coupon_status", conditions.getCouponStatus());
        }
        if (!StringUtils.isEmpty(conditions.getGetTimeBegin())) {
            wrapper.ge("using_time", conditions.getGetTimeBegin());
        }
        if (!StringUtils.isEmpty(conditions.getGetTimeEnd())) {
            wrapper.le("using_time", conditions.getGetTimeEnd());
        }
        Page<CouponUse> page = new Page<>(curNum, pageSize);
        couponUseMapper.selectPage(page, wrapper);
        // 遍历查询到的优惠券信息，根据用户 ID 远程调用接口查询用户信息
        List<CouponUse> records = page.getRecords();
        for (CouponUse record : records) {
            // 远程调用：查询用户信息
            UserInfo userInfo = userFeignClient.getUserById(record.getUserId());
            record.setUserInfo(userInfo);
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public boolean userObtainCoupon(Long userId) {
        // 查询系统中可用的优惠券
        String today = DateUtils.parseDateWithHyphen();
        QueryWrapper<CouponInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("start_time", today);
        queryWrapper.ge("end_time", today);
        List<CouponInfo> couponInfos = baseMapper.selectList(queryWrapper);
        if (couponInfos.size() == 0) {
            return false;
        }

        // 随机挑选一张可用的优惠券发放给用户
        CouponInfo couponInfo = couponInfos.get(NumberUtils.randomNumber(couponInfos.size()));
        if (couponInfo != null) {
            // 将该张优惠券注册到该用户名下
            CouponUse couponUse = new CouponUse();
            couponUse.setUserId(userId);
            couponUse.setCouponId(couponInfo.getId());
            couponUse.setGetTime(new Date());
            // [1]未使用；[2]已使用
            couponUse.setCouponStatus("1");
            // 过期时间
            couponUse.setExpireTime(couponInfo.getExpireTime());
            return couponUseMapper.insert(couponUse) == 1;
        }
        return false;
    }

    @Override
    public List<CouponInfoVo> listUserCoupons(Long userId) {
        return baseMapper.listUserCoupons(userId);
    }
}
