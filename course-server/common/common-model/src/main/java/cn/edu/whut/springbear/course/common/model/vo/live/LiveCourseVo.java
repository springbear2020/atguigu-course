package cn.edu.whut.springbear.course.common.model.vo.live;

import cn.edu.whut.springbear.course.common.model.pojo.live.LiveCourse;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LiveCourseVo extends LiveCourse {
    @ApiModelProperty(value = "主播老师")
    private Teacher teacher;

    private Integer liveStatus;

    @ApiModelProperty(value = "直播开始时间")
    private String startTimeString;

    @ApiModelProperty(value = "直播结束时间")
    private String endTimeString;
}

