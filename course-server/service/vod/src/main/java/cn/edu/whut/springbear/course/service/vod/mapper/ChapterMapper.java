package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.model.pojo.vod.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@Repository
public interface ChapterMapper extends BaseMapper<Chapter> {
    /**
     * 查询课程章节详情（一门课程对应多个章节、一个章节对应多个小节）
     *
     * @param courseId 课程 ID
     * @return 章节详情列表数据
     */
    @Select("select * from chapter where is_deleted = 0 and course_id = #{courseId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "videos", column = "id", many = @Many(select = "cn.edu.whut.springbear.course.service.vod.mapper.VideoMapper.listVideosOfChapter"), javaType = List.class)})
    List<Chapter> listChapterDetails(@Param("courseId") Long courseId);
}
