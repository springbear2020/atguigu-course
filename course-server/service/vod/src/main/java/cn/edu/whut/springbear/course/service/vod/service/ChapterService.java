package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
public interface ChapterService extends IService<Chapter> {
    /**
     * 查询课程下的所有章节，章节下包含小节
     *
     * @param courseId 课程 ID
     * @return 课程的章节详情数据
     */
    List<Chapter> listChaptersOfCourse(Long courseId);

    /**
     * 删除课程下的所有章节
     *
     * @param courseId 课程 ID
     */
    void deleteChaptersOfCourse(Long courseId);
}
