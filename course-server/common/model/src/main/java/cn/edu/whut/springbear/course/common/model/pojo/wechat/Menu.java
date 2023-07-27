package cn.edu.whut.springbear.course.common.model.pojo.wechat;

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
@ApiModel(description = "菜单")
@TableName("menu")
public class Menu extends BaseEntity {
    @ApiModelProperty(value = "id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "网页链接，用户点击菜单可打开链接")
    private String url;

    @ApiModelProperty(value = "菜单 key 值，用于消息接口推送")
    @TableField("menu_key")
    private String menuKey;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}