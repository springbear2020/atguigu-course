package cn.edu.whut.springbear.course.common.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class UserInfoQueryVo {
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "身份证号码")
    private String idNo;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话号码")
    private String phone;
}

