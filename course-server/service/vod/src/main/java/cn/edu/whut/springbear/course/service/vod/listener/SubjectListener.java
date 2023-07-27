package cn.edu.whut.springbear.course.service.vod.listener;

import cn.edu.whut.springbear.course.model.pojo.vod.Subject;
import cn.edu.whut.springbear.course.service.vod.mapper.SubjectMapper;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-22 09:12
 */
@Component
public class SubjectListener extends AnalysisEventListener<Subject> {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(Subject subject, AnalysisContext analysisContext) {
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
