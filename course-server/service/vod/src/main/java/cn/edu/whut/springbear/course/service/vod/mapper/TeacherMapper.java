package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.model.pojo.vod.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 21:26:26
 */
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {
    /**
     * 获取讲师名称
     *
     * @param tid 讲师 ID
     * @return 讲师名称
     */
    @Select("select name from teacher where is_deleted = 0 and id = #{tid}")
    String getTeacherNameById(@Param("tid") Long tid);
}
