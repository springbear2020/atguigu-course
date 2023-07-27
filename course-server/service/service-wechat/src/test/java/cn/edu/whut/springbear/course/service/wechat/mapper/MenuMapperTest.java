package cn.edu.whut.springbear.course.service.wechat.mapper;

import cn.edu.whut.springbear.course.common.model.vo.wechat.MenuVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-25 16:36
 */
@SpringBootTest
class MenuMapperTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    void listMenuDetails() {
        List<MenuVo> menuVos = menuMapper.listMenuDetails(0);
        menuVos.forEach(System.out::println);
    }
}