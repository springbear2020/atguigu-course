package cn.edu.whut.springbear.course.client.vod;

import cn.edu.whut.springbear.course.common.model.pojo.vod.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 16:48
 */
@FeignClient(value = "course-service-vod")
public interface TeacherFeignClient {
    /**
     * 查询讲师
     *
     * @param id 讲师 ID
     * @return 讲师信息
     */
    @GetMapping("feign/vod/teacher/get/{id}")
    Teacher getTeacherLive(@PathVariable Long id);
}
