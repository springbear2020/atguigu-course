package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.VideoVisitor;
import cn.edu.whut.springbear.course.model.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-23
 */
public interface VideoVisitorService extends IService<VideoVisitor> {
    /**
     * 统计课程小节下的观看人数
     *
     * @param courseId  课程 ID
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return [时间-人数]集合列表
     */
    List<VideoVisitorCountVo> countVisitors(Long courseId, String startDate, String endDate);
}
