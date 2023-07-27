package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.model.vo.vod.CourseFormVo;
import cn.edu.whut.springbear.course.model.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
public interface CourseService extends IService<Course> {
    /**
     * 查询课程分页数据
     *
     * @param pageNum       当前页码
     * @param pageSize      每页显示数量
     * @param courseQueryVo 额外查询条件
     * @return 课程分页数据
     */
    Page<Course> listCoursePageData(int pageNum, int pageSize, CourseQueryVo courseQueryVo);

    /**
     * 保存课程信息
     *
     * @param courseFormVo 课程信息
     * @return 成功：返回课程 ID
     */
    Long saveCourse(CourseFormVo courseFormVo);

    Course getCourseDetails(Long courseId);

    boolean updateCourse(CourseFormVo courseFormVo);

    boolean updateCourseStatus(Long courseId);

    boolean deleteCourse(Long courseId);
}
