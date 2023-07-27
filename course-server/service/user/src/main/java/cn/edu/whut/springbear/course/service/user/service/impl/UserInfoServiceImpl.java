package cn.edu.whut.springbear.course.service.user.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import cn.edu.whut.springbear.course.service.user.mapper.UserInfoMapper;
import cn.edu.whut.springbear.course.service.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
