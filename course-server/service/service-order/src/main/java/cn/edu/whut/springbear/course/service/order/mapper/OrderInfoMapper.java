package cn.edu.whut.springbear.course.service.order.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.order.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 订单表 订单表 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-24
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    /**
     * 根据用户 ID 和课程 ID 查询用户的课程订单信息
     *
     * @param userId   用户 ID
     * @param courseId 课程 ID
     * @return 用户课程订单信息
     */
    @Select("select o.* from order_info o, order_detail l where l.course_id = #{courseId} and l.user_id = #{userId} and l.order_id = o.id")
    OrderInfo getOrderOfUser(@Param("userId") Long userId, @Param("courseId") Long courseId);
}
