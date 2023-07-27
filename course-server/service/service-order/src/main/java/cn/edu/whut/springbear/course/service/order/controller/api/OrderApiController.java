package cn.edu.whut.springbear.course.service.order.controller.api;

import cn.edu.whut.springbear.course.common.model.vo.order.OrderFormVo;
import cn.edu.whut.springbear.course.common.model.vo.order.OrderInfoVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 09:59
 */
@RestController
@RequestMapping("api/order")
public class OrderApiController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("save")
    public Result submitOrder(@RequestBody OrderFormVo orderFormVo) {
        // 生成订单，返回生成的订单 ID
        Long orderId = orderInfoService.createOrder(orderFormVo);
        return orderId == null ? Result.fail("新增点播课程订单失败", null) : Result.success("新增点播课程订单成功", orderId);
    }

    @GetMapping("get/{orderId}")
    public Result getOrderInfo(@PathVariable Long orderId) {
        OrderInfoVo orderInfoVo = orderInfoService.getOrderInfoVoById(orderId);
        return orderInfoVo == null ? Result.fail("查询订单信息失败", null) : Result.success("查询订单信息成功", orderInfoVo);
    }

    @PostMapping("pay/{tradeNumber}")
    public Result wechatPay(@PathVariable String tradeNumber) {
        return orderInfoService.updateOrder(tradeNumber) ? Result.success("课程购买成功", null) : Result.fail("订单支付失败", null);
    }
}
