package cn.edu.whut.springbear.course.common.model.pojo.vod;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import cn.edu.whut.springbear.course.common.model.vo.vod.VideoVo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Chapter")
@TableName("chapter")
public class Chapter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 ID")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "章节名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "显示排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("章节下的小节列表")
    @TableField(exist = false)
    private List<VideoVo> videos;
}