package cn.edu.whut.springbear.course.service.live.service;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseConfig;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseConfigVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 直播课程配置表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseConfigService extends IService<LiveCourseConfig> {
    /**
     * 通过课程 ID 查询课程配置信息
     *
     * @param courseId 课程 ID
     * @return 课程配置信息
     */
    LiveCourseConfigVo getCourseConfigByCourseId(Long courseId);

    /**
     * 更新直播课程配置信息
     *
     * @param liveCourseConfigVo 直播课程配置信息
     * @return true：更新成功
     */
    boolean updateConfig(LiveCourseConfigVo liveCourseConfigVo);
}
