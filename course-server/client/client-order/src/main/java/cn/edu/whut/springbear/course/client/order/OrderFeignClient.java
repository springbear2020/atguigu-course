package cn.edu.whut.springbear.course.client.order;

import cn.edu.whut.springbear.course.common.model.pojo.order.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 22:09
 */
@FeignClient(value = "course-service-order")
public interface OrderFeignClient {
    /**
     * 查询用户课程订单支付状态
     *
     * @param userId   用户 ID
     * @param courseId 课程 ID
     * @return 用户课程订单支付状态
     */
    @GetMapping("feign/order/get")
    String getUserCoursePayStatus(@RequestParam Long userId, @RequestParam Long courseId);


    /**
     * 根据订单流水号查询订单
     *
     * @param tradeNumber 订单流水号
     * @return 订单信息
     */
    @GetMapping("feign/order/get/order")
    OrderInfo getOrderInfo(@RequestParam String tradeNumber);
}
