package cn.edu.whut.springbear.course.service.live.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseAccount;
import cn.edu.whut.springbear.course.service.live.mapper.LiveCourseAccountMapper;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseAccountService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 直播课程账号表（受保护信息） 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@Service
public class LiveCourseAccountServiceImpl extends ServiceImpl<LiveCourseAccountMapper, LiveCourseAccount> implements LiveCourseAccountService {

    @Override
    public boolean removeByCourseId(Long liveCourseId) {
        QueryWrapper<LiveCourseAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("live_course_id", liveCourseId);
        return baseMapper.delete(queryWrapper) == 1;
    }

    @Override
    public LiveCourseAccount getByLiveCourseId(Long id) {
        LambdaQueryWrapper<LiveCourseAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LiveCourseAccount::getLiveCourseId, id);
        return baseMapper.selectOne(queryWrapper);
    }
}
