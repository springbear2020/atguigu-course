package cn.edu.whut.springbear.course.service.order.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.order.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单明细 订单明细 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    /**
     * 查询订单中的课程名称
     *
     * @param orderId 订单 ID
     * @return 课程名称
     */
    @Select("select course_name from order_detail where id = #{orderId}")
    String getOrderCourseName(@Param("orderId") Long orderId);
}
