package cn.edu.whut.springbear.course.common.model.vo.vod;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class TeacherQueryVo {
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 [1-高级讲师；2-首席讲师]")
    private Integer level;

    @ApiModelProperty(value = "入驻时间")
    private String joinDateBegin;

    @ApiModelProperty(value = "退出时间")
    private String joinDateEnd;
}

