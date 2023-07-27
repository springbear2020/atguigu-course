package cn.edu.whut.springbear.course.service.live.service;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 直播课程关联推荐表 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
public interface LiveCourseGoodsService extends IService<LiveCourseGoods> {
    /**
     * 通过直播课程 ID 查询直播课程商品信息
     *
     * @param courseId 直播课程 ID
     * @return 直播课程商品信息
     */
    List<LiveCourseGoods> getByLiveCourseId(Long courseId);
}
