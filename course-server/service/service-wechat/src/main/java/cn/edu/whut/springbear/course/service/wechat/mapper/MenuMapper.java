package cn.edu.whut.springbear.course.service.wechat.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.wechat.Menu;
import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据父级菜单 ID 查询菜单详情（一个父级菜单下包含多个子菜单）
     *
     * @param parentId 父级菜单 ID
     * @return 所有菜单详情列表
     */
    @Select("select * from menu where is_deleted = 0 and parent_id = #{parentId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "children", column = "id", many = @Many(select = "cn.edu.whut.springbear.course.service.wechat.mapper.MenuMapper.listMenuDetails"), javaType = List.class)
    })
    List<MenuVo> listMenuDetails(@Param("parentId") Integer parentId);
}
