package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.model.pojo.vod.CourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@Repository
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {
    @Update("update course_description set description = #{newDescription} where course_id = #{courseId}")
    int updateDescriptionByCourseId(@Param("newDescription") String newDescription, @Param("courseId") Long courseId);

    @Select("select description from course_description where is_deleted = 0 and course_id = #{courseId}")
    String getDescriptionByCourseId(@Param("courseId") Long courseId);
}
