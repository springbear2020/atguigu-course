package cn.edu.whut.springbear.course.model.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "管理员登录信息")
public class AdminLoginVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员 id")
    private Long adminId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "仓库 id")
    private Long wareId;
}