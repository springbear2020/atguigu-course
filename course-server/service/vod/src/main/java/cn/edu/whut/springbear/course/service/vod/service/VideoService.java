package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
public interface VideoService extends IService<Video> {
    /**
     * 删除课程下的所有小节
     *
     * @param courseId 课程 ID
     */
    void deleteVideosOfCourse(Long courseId);

    /**
     * 删除章节下的所有小节
     *
     * @param chapterId 章节 ID
     */
    void deleteVideosOfChapter(Long chapterId);

    /**
     * 通过小节 ID 删除小节
     *
     * @param videoId 小节 ID
     */
    boolean deleteVideoById(Long videoId);
}
