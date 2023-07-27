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
@ApiModel(description = "Video")
@TableName("video")
public class Video extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 ID")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "章节 ID")
    @TableField("chapter_id")
    private Long chapterId;

    @ApiModelProperty(value = "节点名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    @TableField("video_source_id")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    @TableField("video_original_name")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    @TableField("play_count")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听 [0-免费；1-收费]")
    @TableField("is_free")
    private Integer isFree;

    @ApiModelProperty(value = "视频时长（秒）")
    @TableField("duration")
    private Float duration;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    @TableField("size")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    @TableField("version")
    private Long version;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;
}