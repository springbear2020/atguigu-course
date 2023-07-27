package cn.edu.whut.springbear.course.service.live.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseConfig;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseGoods;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseConfigVo;
import cn.edu.whut.springbear.course.common.model.vo.live.LiveCourseGoodsView;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.live.mapper.LiveCourseConfigMapper;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseConfigService;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseGoodsService;
import cn.edu.whut.springbear.course.service.live.service.LiveCourseService;
import cn.edu.whut.springbear.course.service.live.util.MTCloud;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 直播课程配置表 服务实现类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-31
 */
@Service
public class LiveCourseConfigServiceImpl extends ServiceImpl<LiveCourseConfigMapper, LiveCourseConfig> implements LiveCourseConfigService {
    @Autowired
    private LiveCourseGoodsService liveCourseGoodsService;
    @Autowired
    private LiveCourseService liveCourseService;
    @Autowired
    private MTCloud mtCloud;

    @Override
    public LiveCourseConfigVo getCourseConfigByCourseId(Long courseId) {
        // 通过课程 ID 查询直播课程配置信息
        QueryWrapper<LiveCourseConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("live_course_id", courseId);
        LiveCourseConfig liveCourseConfig = baseMapper.selectOne(queryWrapper);
        LiveCourseConfigVo liveCourseConfigVo = new LiveCourseConfigVo();

        if (liveCourseConfig != null) {
            List<LiveCourseGoods> liveCourseGoodsList = liveCourseGoodsService.getByLiveCourseId(courseId);
            BeanUtils.copyProperties(liveCourseConfig, liveCourseConfigVo);
            liveCourseConfigVo.setLiveCourseGoodsList(liveCourseGoodsList);
        }
        return liveCourseConfigVo;
    }

    @Override
    public boolean updateConfig(LiveCourseConfigVo liveCourseConfigVo) {
        LiveCourseConfig liveCourseConfigUpt = new LiveCourseConfig();
        BeanUtils.copyProperties(liveCourseConfigVo, liveCourseConfigUpt);

        // 更新或保存课程直播配置信息
        if (liveCourseConfigVo.getId() == null) {
            this.save(liveCourseConfigUpt);
        } else {
            this.updateById(liveCourseConfigUpt);
        }

        // 删除原有的课程商品信息并批量插入新的课程商品信息
        liveCourseGoodsService.remove(new LambdaQueryWrapper<LiveCourseGoods>().eq(LiveCourseGoods::getLiveCourseId, liveCourseConfigVo.getLiveCourseId()));
        if (!CollectionUtils.isEmpty(liveCourseConfigVo.getLiveCourseGoodsList())) {
            liveCourseGoodsService.saveBatch(liveCourseConfigVo.getLiveCourseGoodsList());
        }

        // 将课程直播配置信息同步到欢拓云平台
        HashMap<Object, Object> options = new HashMap<>();
        options.put("pageViewMode", liveCourseConfigVo.getPageViewMode());
        JSONObject number = new JSONObject();
        number.put("enable", liveCourseConfigVo.getNumberEnable());
        options.put("number", number.toJSONString());
        number.put("enable", liveCourseConfigVo.getStoreEnable());
        number.put("type", liveCourseConfigVo.getStoreType());
        options.put("store", number.toJSONString());
        LiveCourse liveCourse = liveCourseService.getById(liveCourseConfigVo.getLiveCourseId());
        List<LiveCourseGoods> liveCourseGoodsList = liveCourseConfigVo.getLiveCourseGoodsList();
        if (!CollectionUtils.isEmpty(liveCourseGoodsList)) {
            List<LiveCourseGoodsView> liveCourseGoodsViewList = new ArrayList<>();
            for (LiveCourseGoods liveCourseGoods : liveCourseGoodsList) {
                LiveCourseGoodsView liveCourseGoodsView = new LiveCourseGoodsView();
                BeanUtils.copyProperties(liveCourseGoods, liveCourseGoodsView);
                liveCourseGoodsViewList.add(liveCourseGoodsView);
            }
            JSONObject goodsListEdit = new JSONObject();
            goodsListEdit.put("status", "0");
            options.put("goodsListEdit ", goodsListEdit.toJSONString());
            options.put("goodsList", JSON.toJSONString(liveCourseGoodsViewList));
        }

        String res;
        try {
            res = mtCloud.courseUpdateLifeConfig(liveCourse.getCourseId().toString(), options);
        } catch (Exception e) {
            throw new CourseException(30000, e.getMessage());
        }
        // 解析欢拓云平台响应的 json 数据
        JSONObject data = JSON.parseObject(res);
        Integer code = data.getInteger("code");
        String msg = data.getString("msg");
        if (code != MTCloud.CODE_SUCCESS) {
            throw new CourseException(30000, "同步课程配置信息失败：" + msg);
        }
        return true;
    }

}
