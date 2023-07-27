package cn.edu.whut.springbear.course.common.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-19 11:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CourseException extends RuntimeException {
    private Integer code;
    private String message;
}
