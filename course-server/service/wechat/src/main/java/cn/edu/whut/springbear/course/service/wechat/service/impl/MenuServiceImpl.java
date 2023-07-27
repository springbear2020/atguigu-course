package cn.edu.whut.springbear.course.service.wechat.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.wechat.Menu;
import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.wechat.mapper.MenuMapper;
import cn.edu.whut.springbear.course.service.wechat.service.MenuService;
import cn.edu.whut.springbear.course.service.wechat.util.PropertyUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> listMenuDetails(Integer parentId) {
        return menuMapper.listMenuDetails(parentId);
    }

    @Override
    public boolean syncMenu() {
        List<MenuVo> menuVoList = this.listMenuDetails(0);

        /*
         * 按微信开发者文档格式组织 JSON 对象
         * ref: https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html
         */
        // 所有的顶级菜单，最多三个
        JSONArray fathers = new JSONArray();
        for (MenuVo menuVo : menuVoList) {
            // 设置顶级菜单名称
            JSONObject father = new JSONObject();
            father.put("name", menuVo.getName());
            // 顶级菜单下的子菜单
            List<MenuVo> children = menuVo.getChildren();
            JSONArray subMenus = new JSONArray();
            for (MenuVo menu : children) {
                // 子菜单的类型及名称
                JSONObject view = new JSONObject();
                view.put("type", menu.getType());
                // 链接型子菜单
                if ("view".equals(menu.getType())) {
                    view.put("name", menu.getName());
                    view.put("url", menu.getUrl());
                } else {
                    // 事件型子菜单
                    view.put("name", menu.getName());
                    view.put("key", menu.getMenuKey());
                }
                subMenus.add(view);
            }
            father.put("sub_button", subMenus);
            fathers.add(father);
        }
        // 顶级 JSON 对象 "button"
        JSONObject button = new JSONObject();
        button.put("button", fathers);

        // 微信公众号管理核心配置
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(PropertyUtils.WECHAT_APP_ID);
        wxMpDefaultConfig.setSecret(PropertyUtils.WECHAT_APP_KEY);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpDefaultConfig);

        try {
            // 同步
            wxMpService.getMenuService().menuCreate(button.toJSONString());
            return true;
        } catch (WxErrorException e) {
            throw new CourseException(30000, e.getMessage());
        }
    }
}
