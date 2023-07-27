package cn.edu.whut.springbear.course.common.model.vo.live;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class LiveVisitorQueryVo {
    @ApiModelProperty(value = "直播课程 id")
    private Long liveCourseId;
}

