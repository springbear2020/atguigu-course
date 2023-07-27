package cn.edu.whut.springbear.course.common.model.pojo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "Subject")
@TableName("subject")
public class Subject {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @ExcelProperty(value = "id", index = 0)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除 [1:已删除；0-未删除]")
    @JsonIgnore
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();

    @ApiModelProperty(value = "类别名称")
    @TableField("title")
    @ExcelProperty(value = "课程分类名称", index = 1)
    private String title;

    @ApiModelProperty(value = "父 ID")
    @TableField("parent_id")
    @ExcelProperty(value = "上级id", index = 2)
    private Long parentId;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    @ExcelProperty(value = "排序", index = 3)
    private Integer sort;

    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;
}