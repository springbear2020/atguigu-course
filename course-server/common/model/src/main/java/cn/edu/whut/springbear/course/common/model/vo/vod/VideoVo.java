package cn.edu.whut.springbear.course.common.model.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel("课时信息")
public class VideoVo {
    @ApiModelProperty(value = "课时 ID")
    private Long id;

    @ApiModelProperty(value = "课时标题")
    private String title;

    @ApiModelProperty(value = "是否可以试听")
    private Integer isFree;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "阿里云视频 ID")
    private String videoSourceId;
}
