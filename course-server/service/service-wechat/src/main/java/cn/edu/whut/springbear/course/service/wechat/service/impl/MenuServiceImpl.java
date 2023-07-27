package cn.edu.whut.springbear.course.service.wechat.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.wechat.Menu;
import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.wechat.mapper.MenuMapper;
import cn.edu.whut.springbear.course.service.wechat.service.MenuService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private WxMpService wxMpService;
    /**
     * 页面跳转型子菜单的根 url
     */
    @Value("${course.viewBaseUrl}")
    private String viewBaseUrl;

    @Override
    public List<MenuVo> listMenuDetails(Integer parentId) {
        return menuMapper.listMenuDetails(parentId);
    }

    @Override
    public boolean syncMenu() {
        List<MenuVo> fatherMenuList = this.listMenuDetails(0);
        // 所有的顶级菜单，最多 3 个
        if (fatherMenuList.size() > 3) {
            return false;
        }

        /*
         * 按微信开发者文档格式组织 JSON 对象以实现微信公众号菜单的更新
         * ref: https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html
         */
        JSONArray fathers = new JSONArray();
        for (MenuVo menuVo : fatherMenuList) {
            // 设置顶级菜单名称
            JSONObject father = new JSONObject();
            father.put("name", menuVo.getName());

            // 顶级菜单下的子菜单，最多 5 个
            List<MenuVo> children = menuVo.getChildren();
            if (children.size() > 5) {
                return false;
            }

            JSONArray subMenus = new JSONArray();
            for (MenuVo menu : children) {
                // 子菜单的类型及名称
                JSONObject view = new JSONObject();
                view.put("type", menu.getType());

                // 页面跳转型子菜单
                if ("view".equals(menu.getType())) {
                    view.put("name", menu.getName());
                    view.put("url", viewBaseUrl + "/#" + menu.getUrl());
                } else {
                    // 事件型子菜单，如回复消息等事件
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

        try {
            // 将最新微信菜单信息同步到微信后台
            wxMpService.getMenuService().menuCreate(button.toJSONString());
            return true;
        } catch (WxErrorException e) {
            throw new CourseException(30000, e.getMessage());
        }
    }
}
