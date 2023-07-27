package cn.edu.whut.springbear.course.common.model.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel("课程观看进度")
public class CourseProgressVo {
    @ApiModelProperty(value = "课程 ID")
    private Long courseId;

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "总时长")
    private Float durationSum;

    @ApiModelProperty(value = "观看进度总时长")
    private Float progressSum;

    @ApiModelProperty(value = "观看进度")
    private Integer progress;
}
