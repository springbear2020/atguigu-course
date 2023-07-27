package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.model.pojo.vod.Video;
import cn.edu.whut.springbear.course.model.vo.vod.VideoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
public interface VideoMapper extends BaseMapper<Video> {
    /**
     * 查询章节下的所有小节
     *
     * @param chapterId 章节 ID
     * @return 小节列表
     */
    @Select("select * from video where is_deleted = 0 and chapter_id = #{chapterId}")
    List<VideoVo> listVideosOfChapter(@Param("chapterId") Long chapterId);
}
