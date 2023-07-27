package cn.edu.whut.springbear.course.service.vod.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author Spring-_-Bear
 * @datetime 2022-10-22 17:54
 */
@SpringBootTest
class CourseDescriptionMapperTest {
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Test
    public void updateDescriptionByCourseId() {
        System.out.println(courseDescriptionMapper.updateDescriptionByCourseId("lcx", 20L));
    }
}