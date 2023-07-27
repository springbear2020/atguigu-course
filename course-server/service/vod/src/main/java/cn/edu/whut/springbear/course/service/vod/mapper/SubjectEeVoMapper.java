package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.common.model.vo.vod.SubjectEeVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 19:28
 */
@Repository
public interface SubjectEeVoMapper {
    /**
     * 获取所有课程分类信息
     *
     * @return 课程分类信息列表
     */
    @Select("select * from subject where is_deleted = 0")
    List<SubjectEeVo> listSubjects();
}
