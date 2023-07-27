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
    List<VideoVisitorCountVo> countVisitors(Long courseId, String startDate, String endDate);
}
