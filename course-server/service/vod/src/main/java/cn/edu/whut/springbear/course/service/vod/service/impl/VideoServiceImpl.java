package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.model.pojo.vod.Video;
import cn.edu.whut.springbear.course.service.vod.mapper.VideoMapper;
import cn.edu.whut.springbear.course.service.vod.service.VideoService;
import cn.edu.whut.springbear.course.service.vod.service.VodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodService vodService;

    @Override
    public void deleteVideosOfCourse(Long courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        // 遍历课程下所有的小节，查看腾讯云视频 ID 是否存在，存在则删除视频
        List<Video> videos = baseMapper.selectList(queryWrapper);
        for (Video video : videos) {
            String videoSourceId = video.getVideoSourceId();
            if (videoSourceId != null) {
                vodService.deleteVideo(videoSourceId);
            }
        }
        // 删除课程下的所有小节
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteVideosOfChapter(Long chapterId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        // 遍历章节下的所有小节，查看腾讯云视频 ID 是否存在，存在则删除视频
        List<Video> videos = baseMapper.selectList(queryWrapper);
        for (Video video : videos) {
            String videoSourceId = video.getVideoSourceId();
            if (videoSourceId != null) {
                vodService.deleteVideo(videoSourceId);
            }
        }
        // 删除课程下的所有小节
        baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean deleteVideoById(Long videoId) {
        Video video = baseMapper.selectById(videoId);
        // 删除腾讯云上存储的视频
        String videoSourceId = video.getVideoSourceId();
        if (vodService.deleteVideo(videoSourceId)) {
            baseMapper.deleteById(videoId);
            return true;
        }
        return false;
    }
}
