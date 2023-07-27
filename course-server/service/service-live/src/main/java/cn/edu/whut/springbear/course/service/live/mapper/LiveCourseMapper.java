package cn.edu.whut.springbear.course.service.live.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 直播课程表 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseMapper extends BaseMapper<LiveCourse> {
    /**
     * 获取最近的直播课程信息
     *
     * @return 最近直播课程列表
     */
    @Select("select * from live_course where date(start_time) >= curdate() order by id desc limit 5")
    List<LiveCourseVo> findLatelyList();
}
