package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Subject;
import cn.edu.whut.springbear.course.common.model.vo.vod.SubjectEeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 获取所有课程分类信息
     *
     * @return 课程分类信息列表
     */
    @Select("select * from subject where is_deleted = 0")
    List<SubjectEeVo> listSubjects();
}
