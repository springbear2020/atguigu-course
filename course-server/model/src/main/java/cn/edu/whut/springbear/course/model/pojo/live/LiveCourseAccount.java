package cn.edu.whut.springbear.course.model.pojo.live;

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
@ApiModel(description = "LiveCourseAccount")
@TableName("live_course_account")
public class LiveCourseAccount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "直播课程 id")
    @TableField("live_course_id")
    private Long liveCourseId;

    @ApiModelProperty(value = "主播登录账号")
    @TableField("live_account")
    private String liveAccount;

    @ApiModelProperty(value = "主播登录密码")
    @TableField("live_password")
    private String livePassword;

    @ApiModelProperty(value = "主播登录秘钥")
    @TableField("live_key")
    private String liveKey;

    @ApiModelProperty(value = "助教登录秘钥")
    @TableField("admin_key")
    private String adminKey;

    @ApiModelProperty(value = "学生登录秘钥")
    @TableField("user_key")
    private String userKey;
}