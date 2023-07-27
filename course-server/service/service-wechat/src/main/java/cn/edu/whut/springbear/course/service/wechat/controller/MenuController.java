package cn.edu.whut.springbear.course.service.wechat.controller;


import cn.edu.whut.springbear.course.common.model.pojo.wechat.Menu;
import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.wechat.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@RestController
@Api(tags = "微信菜单管理")
@RequestMapping("/admin/wechat/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Menu menu) {
        return menuService.save(menu) ? Result.success("新增公众号菜单成功", null) : Result.fail("新增公众号菜单失败", null);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return menuService.removeById(id) ? Result.success("删除公众号菜单成功", null) : Result.fail("删除公众号菜单失败", null);
    }

    @ApiOperation("修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Menu menu) {
        return menuService.updateById(menu) ? Result.success("修改公众号菜单成功", null) : Result.fail("修改公众号菜单成功", null);
    }

    @ApiOperation("查询菜单")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return menu == null ? Result.fail("查询公众号菜单失败", null) : Result.success("查询公众号菜单成功", menu);
    }

    @ApiOperation("查询所有菜单详情")
    @GetMapping("list")
    public Result listAllMenus() {
        List<MenuVo> menuVos = menuService.listMenuDetails(0);
        return menuVos.isEmpty() ? Result.fail("查询所有菜单详情失败", null) : Result.success("查询公众号菜单详情成功", menuVos);
    }

    @ApiOperation("同步菜单到微信后台")
    @GetMapping("sync")
    public Result syncMenus() {
        return menuService.syncMenu() ? Result.success("同步公众号菜单成功", null) : Result.fail("同步公众号菜单失败", null);
    }
}

