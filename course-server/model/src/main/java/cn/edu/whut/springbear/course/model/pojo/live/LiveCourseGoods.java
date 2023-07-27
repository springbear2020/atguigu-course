package cn.edu.whut.springbear.course.model.pojo.live;

import cn.edu.whut.springbear.course.model.pojo.base.BaseEntity;
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
@ApiModel(description = "LiveCourseGoods")
@TableName("live_course_goods")
public class LiveCourseGoods extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "直播课程 id")
    @TableField("live_course_id")
    private Long liveCourseId;

    @ApiModelProperty(value = "推荐点播课程 id")
    @TableField("goods_id")
    private Long goodsId;

    @ApiModelProperty(value = "商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "图片")
    @TableField("img")
    private String img;

    @ApiModelProperty(value = "商品现价")
    @TableField("price")
    private String price;

    @ApiModelProperty(value = "商品原价")
    @TableField("originalPrice")
    private String originalPrice;

    @ApiModelProperty(value = "商品标签")
    @TableField("tab")
    private Integer tab;

    @ApiModelProperty(value = "商品链接")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "商品状态 [0-下架；1-上架；2-推荐]")
    @TableField("putAway")
    private String putAway;

    @ApiModelProperty(value = "购买模式 [1-链接购买；2-二维码购买]")
    @TableField("pay")
    private Integer pay;

    @ApiModelProperty(value = "商品二维码")
    @TableField("qrcode")
    private String qrcode;
}