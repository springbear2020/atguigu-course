package cn.edu.whut.springbear.course.service.order.controller;


import cn.edu.whut.springbear.course.model.pojo.order.OrderInfo;
import cn.edu.whut.springbear.course.model.vo.order.OrderInfoQueryVo;
import cn.edu.whut.springbear.course.service.order.service.OrderInfoService;
import cn.edu.whut.springbear.course.service.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Api("订单管理接口")
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation("查询订单分页数据")
    @GetMapping("page/{curNum}/{pageSize}")
    public Result orderPageData(
            @ApiParam(name = "curNum", value = "当前页码") @PathVariable Integer curNum,
            @ApiParam(name = "pageSize", value = "每页显示的数量") @PathVariable Integer pageSize,
            OrderInfoQueryVo orderInfoQueryVo) {
        Page<OrderInfo> page = orderInfoService.getOrderPageData(curNum, pageSize, orderInfoQueryVo);
        return Result.success("查询订单分页数据成功", page);
    }
}

