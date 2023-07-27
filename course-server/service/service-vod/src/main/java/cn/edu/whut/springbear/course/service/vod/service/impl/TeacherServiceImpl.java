package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import cn.edu.whut.springbear.course.common.model.vo.vod.TeacherQueryVo;
import cn.edu.whut.springbear.course.service.vod.mapper.TeacherMapper;
import cn.edu.whut.springbear.course.service.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 21:26:26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Page<Teacher> listPageData(int pageNum, int pageSize, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        // 拼接分页查询条件
        if (!StringUtils.isEmpty(teacherQueryVo.getName())) {
            queryWrapper.like("name", teacherQueryVo.getName());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getLevel())) {
            queryWrapper.eq("level", teacherQueryVo.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getJoinDateBegin())) {
            queryWrapper.ge("join_date", teacherQueryVo.getJoinDateBegin());
        }
        if (!StringUtils.isEmpty(teacherQueryVo.getJoinDateEnd())) {
            queryWrapper.le("join_date", teacherQueryVo.getJoinDateEnd());
        }
        Page<Teacher> pageParam = new Page<>(pageNum, pageSize);
        this.page(pageParam, queryWrapper);
        return pageParam;
    }
}
