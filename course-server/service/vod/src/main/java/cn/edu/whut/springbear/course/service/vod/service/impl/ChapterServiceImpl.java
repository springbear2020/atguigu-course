package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Chapter;
import cn.edu.whut.springbear.course.service.vod.mapper.ChapterMapper;
import cn.edu.whut.springbear.course.service.vod.service.ChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<Chapter> listChaptersOfCourse(Long courseId) {
        return chapterMapper.listChapterDetails(courseId);
    }

    @Override
    public void deleteChaptersOfCourse(Long courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        chapterMapper.delete(queryWrapper);
    }
}
