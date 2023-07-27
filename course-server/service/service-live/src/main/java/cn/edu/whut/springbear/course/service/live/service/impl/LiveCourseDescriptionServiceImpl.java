package cn.edu.whut.springbear.course.service.live.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseDescription;
import cn.edu.whut.springbear.course.service.live.mapper.LiveCourseDescriptionMapper;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@Service
public class LiveCourseDescriptionServiceImpl extends ServiceImpl<LiveCourseDescriptionMapper, LiveCourseDescription> implements LiveCourseDescriptionService {

    @Override
    public boolean removeByCourseId(Long liveCourseId) {
        QueryWrapper<LiveCourseDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("live_course_id", liveCourseId);
        return baseMapper.delete(queryWrapper) == 1;
    }

    @Override
    public LiveCourseDescription getByLiveCourseId(Long courseId) {
        QueryWrapper<LiveCourseDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("live_course_id", courseId);
        return baseMapper.selectOne(queryWrapper);
    }
}
