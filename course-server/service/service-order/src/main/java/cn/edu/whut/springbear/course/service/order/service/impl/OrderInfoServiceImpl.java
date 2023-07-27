package cn.edu.whut.springbear.course.service.order.service.impl;

import cn.edu.whut.springbear.course.client.activity.CouponFeignClient;
import cn.edu.whut.springbear.course.client.vod.CourseFeignClient;
import cn.edu.whut.springbear.course.client.user.UserFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.activity.CouponInfo;
import cn.edu.whut.springbear.course.common.model.pojo.order.OrderDetail;
import cn.edu.whut.springbear.course.common.model.pojo.order.OrderInfo;
import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.common.model.vo.order.OrderFormVo;
import cn.edu.whut.springbear.course.common.model.vo.order.OrderInfoQueryVo;
import cn.edu.whut.springbear.course.common.model.vo.order.OrderInfoVo;
import cn.edu.whut.springbear.course.common.util.NumberUtils;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.common.util.interceptor.AuthContextHolder;
import cn.edu.whut.springbear.course.service.order.mapper.OrderDetailMapper;
import cn.edu.whut.springbear.course.service.order.mapper.OrderInfoMapper;
import cn.edu.whut.springbear.course.service.order.service.OrderDetailService;
import cn.edu.whut.springbear.course.service.order.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private CourseFeignClient courseFeignClient;
    @Autowired
    private CouponFeignClient couponFeignClient;

    @Override
    public Long createOrder(OrderFormVo orderFormVo) {
        // 读取从登录拦截器中拦截到的 token 中获取用户 uid
        Long userId = AuthContextHolder.getUserId();

        // 查询当前用户、当前课程是否已存在订单信息
        Long courseId = orderFormVo.getCourseId();
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getCourseId, courseId);
        queryWrapper.eq(OrderDetail::getUserId, userId);
        OrderDetail orderDetail = orderDetailService.getOne(queryWrapper);
        if (orderDetail != null) {
            // 如果订单已存在，则直接返回订单 id
            return orderDetail.getId();
        }

        // 查询课程信息
        Course course = courseFeignClient.getCourse(courseId);
        if (course == null) {
            throw new CourseException(30000, "课程信息不存在");
        }

        // 查询用户信息
        UserInfo userInfo = userFeignClient.getUserById(userId);
        if (userInfo == null) {
            throw new CourseException(30000, "用户信息不存在");
        }

        // 查询用户所选择的优惠券信息
        Long couponId = orderFormVo.getCouponId();
        BigDecimal couponReduce = new BigDecimal(0);
        if (couponId != null) {
            CouponInfo couponInfo = couponFeignClient.getCouponInfo(couponId);
            if (couponInfo == null) {
                throw new CourseException(30000, "优惠券信息不存在");
            }
            couponReduce = couponInfo.getAmount();
        }

        // 创建订单并保存
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setNickName(userInfo.getNickName());
        orderInfo.setPhone(userInfo.getPhone());
        orderInfo.setProvince(userInfo.getProvince());
        orderInfo.setOriginAmount(course.getPrice());
        orderInfo.setCouponReduce(couponReduce);
        orderInfo.setFinalAmount(orderInfo.getOriginAmount().subtract(orderInfo.getCouponReduce()));
        orderInfo.setOutTradeNo(NumberUtils.orderNumber());
        orderInfo.setTradeBody(course.getTitle());
        orderInfo.setOrderStatus("UNPAID");
        this.save(orderInfo);
        // 保存订单详情
        orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderInfo.getId());
        orderDetail.setUserId(userId);
        orderDetail.setCourseId(courseId);
        orderDetail.setCourseName(course.getTitle());
        orderDetail.setCover(course.getCover());
        orderDetail.setOriginAmount(course.getPrice());
        orderDetail.setCouponReduce(new BigDecimal(0));
        orderDetail.setFinalAmount(orderDetail.getOriginAmount().subtract(orderDetail.getCouponReduce()));
        orderDetailService.save(orderDetail);

        // 更新优惠券使用状态
        Long couponUseId = orderFormVo.getCouponUseId();
        if (couponUseId != null) {
            if (!couponFeignClient.updateCouponInfoUseStatus(couponUseId, orderInfo.getId())) {
                throw new CourseException(30000, "更新优惠券使用状态失败");
            }
        }
        return orderInfo.getId();
    }

    @Override
    public Page<OrderInfo> getOrderPageData(Integer curNum, Integer pageSize, OrderInfoQueryVo conditions) {
        // 判断条件值是否为空，不为空则进行条件封装
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(conditions.getOrderStatus())) {
            wrapper.eq("order_status", conditions.getOrderStatus());
        }
        if (!StringUtils.isEmpty(conditions.getUserId())) {
            wrapper.eq("user_id", conditions.getUserId());
        }
        if (!StringUtils.isEmpty(conditions.getOutTradeNo())) {
            wrapper.eq("out_trade_no", conditions.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(conditions.getPhone())) {
            wrapper.eq("phone", conditions.getPhone());
        }
        if (!StringUtils.isEmpty(conditions.getCreateTimeBegin())) {
            wrapper.ge("create_time", conditions.getCreateTimeBegin());
        }
        if (!StringUtils.isEmpty(conditions.getCreateTimeEnd())) {
            wrapper.le("create_time", conditions.getCreateTimeEnd());
        }

        Page<OrderInfo> page = new Page<>(curNum, pageSize);
        baseMapper.selectPage(page, wrapper);
        // 遍历所有订单信息，查询每条订单的课程信息
        List<OrderInfo> records = page.getRecords();
        for (OrderInfo record : records) {
            String orderCourseName = orderDetailMapper.getOrderCourseName(record.getId());
            record.setCourseName(orderCourseName);
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public OrderInfoVo getOrderInfoVoById(Long orderId) {
        OrderInfo orderInfo = this.getById(orderId);
        OrderDetail orderDetail = orderDetailService.getById(orderId);

        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo, orderInfoVo);
        orderInfoVo.setCourseId(orderDetail.getCourseId());
        orderInfoVo.setCover(orderDetail.getCover());
        orderInfoVo.setCourseName(orderDetail.getCourseName());
        return orderInfoVo;
    }

    @Override
    public boolean updateOrder(String tradeNumber) {
        // 根据 out_trade_no 查询订单
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOutTradeNo, tradeNumber);
        OrderInfo orderInfo = baseMapper.selectOne(wrapper);
        // 更新订单状态为已支付
        orderInfo.setOrderStatus("PAID");
        orderInfo.setPayTime(new Date());
        return baseMapper.updateById(orderInfo) == 1;
    }

    @Override
    public String payStatus(Long userId, Long courseId) {
        OrderInfo orderOfUser = baseMapper.getOrderOfUser(userId, courseId);
        return orderOfUser.getOrderStatus();
    }
}
