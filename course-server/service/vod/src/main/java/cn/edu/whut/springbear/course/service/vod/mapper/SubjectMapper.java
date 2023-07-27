package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.model.pojo.vod.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 16:18
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {
    /**
     * 获取课程分类名称
     *
     * @param id 分类 ID
     * @return 课程分类名称
     */
    @Select("select title from subject where is_deleted = 0 and id = #{id}")
    String getSubjectNameById(@Param("id") Long id);
}
