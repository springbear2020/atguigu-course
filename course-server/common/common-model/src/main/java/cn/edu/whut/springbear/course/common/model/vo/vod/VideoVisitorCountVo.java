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
public class VideoVisitorCountVo {
    @ApiModelProperty(value = "访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;

    @ApiModelProperty(value = "用户个数")
    private Integer userCount;
}