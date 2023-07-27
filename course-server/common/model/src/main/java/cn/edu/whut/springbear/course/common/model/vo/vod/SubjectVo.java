package cn.edu.whut.springbear.course.common.model.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel("课程分类列表")
public class SubjectVo {
    @ApiModelProperty(value = "课程分类 ID")
    private Long id;

    @ApiModelProperty(value = "课程分类名称")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "课程二级分类列表")
    private List<SubjectVo> children = new ArrayList<>();
}
