package cn.edu.whut.springbear.course.common.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-19 09:54
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "描述消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Object data;

    public Result() {
    }

    private static Result build(Integer code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result success(String message, Object data) {
        return build(20000, message, data);
    }

    public static Result fail(String message, Object data) {
        return build(30000, message, data);
    }

    public Result changeCode(int newCode) {
        this.setCode(newCode);
        return this;
    }
}