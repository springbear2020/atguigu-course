package cn.edu.whut.springbear.course.common.model.vo.wechat;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "菜单")
public class MenuVo {
    private Long id;

    @ApiModelProperty(value = "id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "菜单 key")
    private String menuKey;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "下级")
    @TableField(exist = false)
    private List<MenuVo> children;
}