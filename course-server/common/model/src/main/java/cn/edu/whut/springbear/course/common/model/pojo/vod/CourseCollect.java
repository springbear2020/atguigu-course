package cn.edu.whut.springbear.course.common.model.pojo.vod;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "CourseCollect")
@TableName("course_collect")
public class CourseCollect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程讲师 ID")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "会员 ID")
    @TableField("user_id")
    private Long userId;
}