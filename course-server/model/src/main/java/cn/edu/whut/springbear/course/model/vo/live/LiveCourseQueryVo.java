package cn.edu.whut.springbear.course.model.vo.live;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class LiveCourseQueryVo {
    @ApiModelProperty(value = "课程 id")
    private Long courseId;

    @ApiModelProperty(value = "直播名称")
    private String courseName;

    @ApiModelProperty(value = "直播开始时间")
    private Date startTime;

    @ApiModelProperty(value = "直播结束时间")
    private Date endTime;

    @ApiModelProperty(value = "接入方主播账号或 ID 或手机号等，最长 32 位")
    private String account;

    @ApiModelProperty(value = "主播表 id")
    private Long liverId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否逻辑删除")
    private Integer isDeleted;
}

