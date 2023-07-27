package cn.edu.whut.springbear.course.service.order.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.order.OrderInfo;
import cn.edu.whut.springbear.course.common.model.vo.order.OrderInfoQueryVo;
import cn.edu.whut.springbear.course.service.order.mapper.OrderDetailMapper;
import cn.edu.whut.springbear.course.service.order.mapper.OrderInfoMapper;
import cn.edu.whut.springbear.course.service.order.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        // TODO optimize
        List<OrderInfo> records = page.getRecords();
        for (OrderInfo record : records) {
            String orderCourseName = orderDetailMapper.getOrderCourseName(record.getId());
            record.setCourseName(orderCourseName);
        }
        page.setRecords(records);
        return page;
    }
}
