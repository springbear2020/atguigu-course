package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-22 16:25
 */
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void allTeachers() {
        List<Teacher> list = teacherService.list();
        list.forEach(System.out::println);
    }
}
