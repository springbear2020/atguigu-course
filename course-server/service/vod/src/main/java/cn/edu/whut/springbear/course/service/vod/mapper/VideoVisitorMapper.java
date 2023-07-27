package cn.edu.whut.springbear.course.service.vod.mapper;

import cn.edu.whut.springbear.course.common.model.pojo.vod.VideoVisitor;
import cn.edu.whut.springbear.course.common.model.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-23
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {
    /**
     * 统计指定时间内的课程访问量
     *
     * @param courseId  课程 ID
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 课程名称及其访问人数
     */
    @Select("select date(join_time) as joinDate, count(*) as userCount from video_visitor where course_id = #{courseId} and date(join_time) >= #{startDate} and date(join_time) <= #{endDate} group by joinDate order by joinDate")
    List<VideoVisitorCountVo> countVisitors(@Param("courseId") Long courseId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
