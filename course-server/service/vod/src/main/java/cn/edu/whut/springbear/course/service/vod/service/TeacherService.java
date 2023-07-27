package cn.edu.whut.springbear.course.service.vod.service;

import cn.edu.whut.springbear.course.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.model.vo.vod.TeacherQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 21:26:26
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 查询讲师分页数据
     *
     * @param pageNum        当前页码
     * @param pageSize       每页显示的数量
     * @param teacherQueryVo 额外查询条件
     * @return 分页数据
     */
    Page<Teacher> listPageData(int pageNum, int pageSize, TeacherQueryVo teacherQueryVo);
}
