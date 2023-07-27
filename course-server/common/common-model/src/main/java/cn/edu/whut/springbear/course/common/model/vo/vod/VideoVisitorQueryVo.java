package cn.edu.whut.springbear.course.common.model.vo.vod;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class VideoVisitorQueryVo {
    @ApiModelProperty(value = "课程 id")
    private Long courseId;

    @ApiModelProperty(value = "视频 id")
    private Long videoId;

    @ApiModelProperty(value = "进入时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinTime;
}

