package cn.edu.whut.springbear.course.model.pojo.vod;

import cn.edu.whut.springbear.course.model.pojo.base.BaseEntity;
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
@ApiModel(description = "Comment")
@TableName("comment")
public class Comment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "讲师 id")
    @TableField("teacher_id")
    private Long teacherId;

    @ApiModelProperty(value = "会员 id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "会员昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    @TableField("content")
    private String content;
}