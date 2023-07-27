package cn.edu.whut.springbear.course.common.util.exception;


import cn.edu.whut.springbear.course.common.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-19 11:44
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(CourseException.class)
    public Result courseException(CourseException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage(), null).changeCode(e.getCode());
    }
}
