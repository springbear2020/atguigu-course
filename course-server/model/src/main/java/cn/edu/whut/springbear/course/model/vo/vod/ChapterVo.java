package cn.edu.whut.springbear.course.model.vo.vod;

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
@ApiModel("课程章节对象")
public class ChapterVo {
    @ApiModelProperty(value = "章节 ID")
    private Long id;

    @ApiModelProperty(value = "章节标题")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "章节下的课时列表")
    private List<VideoVo> children = new ArrayList<>();
}