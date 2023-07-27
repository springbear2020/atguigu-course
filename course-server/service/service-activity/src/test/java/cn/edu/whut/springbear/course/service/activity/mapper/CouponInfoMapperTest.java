package cn.edu.whut.springbear.course.service.activity.mapper;

import cn.edu.whut.springbear.course.common.model.vo.activity.CouponInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 17:11
 */
@SpringBootTest
class CouponInfoMapperTest {
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Test
    void listUserCoupons() {
        List<CouponInfoVo> couponInfoVos = couponInfoMapper.listUserCoupons(34L);
        for (CouponInfoVo couponInfoVo : couponInfoVos) {
            System.out.println(couponInfoVo);
        }
    }
}