package cn.edu.whut.springbear.course.common.model.vo.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class VideoVisitorVo {
    @ApiModelProperty(value = "课程 id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "视频 id")
    @TableField("video_id")
    private Long videoId;

    @ApiModelProperty(value = "来访者用户 id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "进入时间")
    @TableField("join_time")
    private String joinTime;

    @ApiModelProperty(value = "用户停留的时间(单位：秒)")
    @TableField("duration")
    private Long duration;
}

