package cn.edu.whut.springbear.course.common.model.vo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class SubjectEeVo {
    @ExcelProperty(value = "id", index = 0)
    private Long id;

    @ExcelProperty(value = "课程分类名称", index = 1)
    private String title;

    @ExcelProperty(value = "上级id", index = 2)
    private Long parentId;

    @ExcelProperty(value = "排序", index = 3)
    private Integer sort;
}

