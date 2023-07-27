package cn.edu.whut.springbear.course.service.live.service;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 直播课程账号表（受保护信息） 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseAccountService extends IService<LiveCourseAccount> {
    /**
     * 通过直播课程 ID 删除直播账号信息
     *
     * @param liveCourseId 直播课程 ID
     * @return true：删除成功
     */
    boolean removeByCourseId(Long liveCourseId);

    /**
     * 根据课程 ID 查询直播课程账号信息
     *
     * @param id 课程 ID
     * @return 直播课程账号信息
     */
    LiveCourseAccount getByLiveCourseId(Long id);
}
