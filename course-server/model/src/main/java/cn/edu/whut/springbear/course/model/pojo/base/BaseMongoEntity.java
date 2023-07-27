package cn.edu.whut.springbear.course.model.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class BaseMongoEntity implements Serializable {
    @Id
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @LastModifiedDate
    private Date updateTime;

    /**
     * Transient：该字段将不会被录入到数据库中。只作为普通的 java bean 属性
     */
    @Transient
    @ApiModelProperty(value = "其他参数")
    private Map<String, Object> param = new HashMap<>();
}
