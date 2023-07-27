package cn.edu.whut.springbear.course.model.pojo.user;

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
@ApiModel(description = "UserLoginLog")
@TableName("user_login_log")
public class UserLoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户 id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "登录 ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "登录城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "登录类型 [0-电脑端；1-移动端]")
    @TableField("type")
    private Boolean type;
}