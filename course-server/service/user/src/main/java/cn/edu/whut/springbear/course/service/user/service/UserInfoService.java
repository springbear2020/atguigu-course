package cn.edu.whut.springbear.course.service.user.service;

import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 通过 openId 查询用户
     */
    UserInfo getUserByOpenId(String openId);
}
