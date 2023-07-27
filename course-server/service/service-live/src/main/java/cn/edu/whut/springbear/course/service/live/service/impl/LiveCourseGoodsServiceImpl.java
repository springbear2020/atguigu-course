package cn.edu.whut.springbear.course.service.live.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseGoods;
import cn.edu.whut.springbear.course.service.live.mapper.LiveCourseGoodsMapper;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 直播课程关联推荐表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@Service
public class LiveCourseGoodsServiceImpl extends ServiceImpl<LiveCourseGoodsMapper, LiveCourseGoods> implements LiveCourseGoodsService {
    @Override
    public List<LiveCourseGoods> getByLiveCourseId(Long courseId) {
        return baseMapper.selectList(new LambdaQueryWrapper<LiveCourseGoods>().eq(LiveCourseGoods::getLiveCourseId, courseId));
    }
}
