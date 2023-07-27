package cn.edu.whut.springbear.course.service.user.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.user.UserLoginLog;
import cn.edu.whut.springbear.course.service.user.mapper.UserLoginLogMapper;
import cn.edu.whut.springbear.course.service.user.service.UserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登陆记录表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
