package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Subject;
import cn.edu.whut.springbear.course.common.model.vo.vod.SubjectEeVo;
import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.vod.listener.SubjectListener;
import cn.edu.whut.springbear.course.service.vod.mapper.SubjectEeVoMapper;
import cn.edu.whut.springbear.course.service.vod.mapper.SubjectMapper;
import cn.edu.whut.springbear.course.service.vod.service.SubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 16:19
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectEeVoMapper subjectEeVoMapper;
    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> listSubCourses(long parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void exportCourseData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 获取学科分类数据
            List<SubjectEeVo> subjectEeVos = subjectEeVoMapper.listSubjects();
            // 写入 Excel
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(subjectEeVos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CourseException(30000, e.getMessage());
        }
    }

    @Override
    public boolean importCourseData(MultipartFile file, String realPath) {
        try {
            // 逐行读取 excel 文件中的课程数据并导入
            EasyExcel.read(file.getInputStream(), Subject.class, subjectListener).sheet().doRead();

            // 保存文件到本地磁盘
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;
            file.transferTo(new File(realPath + "/" + newFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
