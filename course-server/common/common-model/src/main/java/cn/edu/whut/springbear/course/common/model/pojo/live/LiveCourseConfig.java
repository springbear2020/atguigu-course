package cn.edu.whut.springbear.course.common.model.pojo.live;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "LiveCourseConfig")
@TableName("live_course_config")
public class LiveCourseConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "直播课程 id")
    @TableField("live_course_id")
    private Long liveCourseId;

    @ApiModelProperty(value = "界面模式 [1-全屏模式；0-二分屏；2-课件模式]")
    @TableField("page_view_mode")
    private Integer pageViewMode;

    @ApiModelProperty(value = "是否开启 [0-否；1-是]")
    @TableField("number_enable")
    private Integer numberEnable;

    @ApiModelProperty(value = "商城是否开启 [0-未开启；1-开启]")
    @TableField("store_enable")
    private Integer storeEnable;

    @ApiModelProperty(value = "1-商品列表；2-商城链接；3-商城二维码")
    @TableField("store_type")
    private Integer storeType;
}