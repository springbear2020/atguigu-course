package cn.edu.whut.springbear.course.common.model.pojo.live;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "LiveCourse")
@TableName("live_course")
public class LiveCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "直播名称")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty(value = "直播开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "直播结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "主播老师 id")
    @TableField("teacher_id")
    private Long teacherId;

    @ApiModelProperty(value = "课程封面图片路径")
    @TableField("cover")
    private String cover;
}