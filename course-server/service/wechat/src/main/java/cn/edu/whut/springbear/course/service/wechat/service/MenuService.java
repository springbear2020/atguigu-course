package cn.edu.whut.springbear.course.service.wechat.service;

import cn.edu.whut.springbear.course.common.model.pojo.wechat.Menu;
import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据父级菜单 ID 查询菜单详情（一个父级菜单下包含多个子菜单）
     *
     * @param parentId 父级菜单 ID
     * @return 所有菜单详情列表
     */
    List<MenuVo> listMenuDetails(Integer parentId);

    /**
     * 同步最新菜单到微信后台
     *
     * @return true：同步成功
     */
    boolean syncMenu();
}
