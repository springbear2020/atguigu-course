package cn.edu.whut.springbear.course.common.model.vo.live;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseConfig;
import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourseGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "LiveCourseConfig")
public class LiveCourseConfigVo extends LiveCourseConfig {
    @ApiModelProperty(value = "商品列表")
    private List<LiveCourseGoods> liveCourseGoodsList;
}