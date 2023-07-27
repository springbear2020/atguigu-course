package cn.edu.whut.springbear.course.service.vod.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-22 15:28
 */
@SpringBootTest
class TeacherMapperTest {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void getTeacherNameById() {
        System.out.println(teacherMapper.getTeacherNameById(1L));
    }
}