package cn.edu.whut.springbear.course.model.vo.live;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "LiveCourseGoods")
public class LiveCourseGoodsView {

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "商品现价")
    private String price;

    @ApiModelProperty(value = "商品原价")
    private String originalPrice;

    @ApiModelProperty(value = "商品标签")
    private String tab;

    @ApiModelProperty(value = "商品链接")
    private String url;

    @ApiModelProperty(value = "商品状态 [0-下架；1-上架；2-推荐]")
    private String putAway;

    @ApiModelProperty(value = "购买模式 [1-链接购买；2-二维码购买]")
    private String pay;

    @ApiModelProperty(value = "商品二维码")
    private String qrcode = "";
}