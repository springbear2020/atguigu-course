package cn.edu.whut.springbear.course.service.order.controller.feign;

import cn.edu.whut.springbear.course.common.model.pojo.order.OrderInfo;
import cn.edu.whut.springbear.course.service.order.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 22:10
 */
@RestController
@RequestMapping("feign/order")
public class OrderFeignController {
    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("get")
    public String getUserCoursePayStatus(@RequestParam Long userId, @RequestParam Long courseId) {
        return orderInfoService.payStatus(userId, courseId);
    }

    @GetMapping("get/order")
    public OrderInfo getOrderInfo(@RequestParam String tradeNumber) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", tradeNumber);
        return orderInfoService.getOne(queryWrapper);
    }
}
