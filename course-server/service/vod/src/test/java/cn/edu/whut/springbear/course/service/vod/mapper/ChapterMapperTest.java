package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Chapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-22 21:10
 */
@SpringBootTest
class ChapterMapperTest {
    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void listChapterDetails() {
        List<Chapter> chapters = chapterMapper.listChapterDetails(18L);
        chapters.forEach(System.out::println);
    }
}