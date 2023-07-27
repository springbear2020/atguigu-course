package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.model.pojo.vod.VideoVisitor;
import cn.edu.whut.springbear.course.model.vo.vod.VideoVisitorCountVo;
import cn.edu.whut.springbear.course.service.vod.mapper.VideoVisitorMapper;
import cn.edu.whut.springbear.course.service.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-23
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public List<VideoVisitorCountVo> countVisitors(Long courseId, String startDate, String endDate) {
        return baseMapper.countVisitors(courseId, startDate, endDate);
    }
}
