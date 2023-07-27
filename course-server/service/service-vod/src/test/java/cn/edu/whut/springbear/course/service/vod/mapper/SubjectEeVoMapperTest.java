package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.common.model.vo.vod.SubjectEeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 19:30
 */
@SpringBootTest
public class SubjectEeVoMapperTest {
    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    public void listSubjects() {
        List<SubjectEeVo> subjectEeVos = subjectMapper.listSubjects();
        subjectEeVos.forEach(System.out::println);
    }

    @Test
    public void getSubjectNameById() {
        System.out.println(subjectMapper.getSubjectNameById(1L));
    }
}
