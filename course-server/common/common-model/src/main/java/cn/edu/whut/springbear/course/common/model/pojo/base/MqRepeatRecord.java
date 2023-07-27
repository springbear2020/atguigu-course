package cn.edu.whut.springbear.course.common.model.pojo.base;

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
@ApiModel(description = "MqRepeatRecord")
@TableName("mq_repeat_record")
public class MqRepeatRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务编号")
    @TableField("business_no")
    private String businessNo;
}