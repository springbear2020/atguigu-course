package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Video;
import cn.edu.whut.springbear.course.service.vod.mapper.VideoMapper;
import cn.edu.whut.springbear.course.service.vod.service.VideoService;
import cn.edu.whut.springbear.course.service.vod.service.VodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        // 删除腾讯云上存储的小节视频
        this.deleteCloudVideos(queryWrapper);
        // 删除课程下的所有小节
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteVideosOfChapter(Long chapterId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        // 删除腾讯云上存储的小节视频
        this.deleteCloudVideos(queryWrapper);
        // 删除课程下的所有小节
        baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean deleteVideoById(Long videoId) {
        Video video = baseMapper.selectById(videoId);
        String videoSourceId = video.getVideoSourceId();
        // 删除腾讯云上存储的视频
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodService.deleteVideo(videoSourceId);
        }
        // 删除小节记录
        return baseMapper.deleteById(videoId) == 1;
    }

    /**
     * 删除腾讯云存储的小节视频资源
     */
    private void deleteCloudVideos(QueryWrapper<Video> queryWrapper) {
        // 遍历所有小节，查看腾讯云视频 ID 是否存在，存在则删除视频
        List<Video> videos = baseMapper.selectList(queryWrapper);
        for (Video video : videos) {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                vodService.deleteVideo(videoSourceId);
            }
        }
    }
}
