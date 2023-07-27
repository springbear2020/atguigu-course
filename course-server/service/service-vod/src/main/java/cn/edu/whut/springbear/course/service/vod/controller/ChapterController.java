package cn.edu.whut.springbear.course.service.vod.controller;


import cn.edu.whut.springbear.course.common.model.pojo.vod.Chapter;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.vod.service.ChapterService;
import cn.edu.whut.springbear.course.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-22
 */
@RestController
@Api(tags = "章节管理接口")
@RequestMapping("/admin/vod/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增章节")
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        return chapterService.save(chapter) ? Result.success("新增章节成功", null) : Result.fail("新增章节失败", null);
    }

    @ApiOperation("删除章节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        // 删除章节前先删除章节下的所有小节
        videoService.deleteVideosOfChapter(id);
        return chapterService.removeById(id) ? Result.success("删除章节成功", null) : Result.fail("删除章节失败", null);
    }

    @ApiOperation("更新章节")
    @PutMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        return chapterService.updateById(chapter) ? Result.success("更新章节成功", null) : Result.fail("更新章节失败", null);
    }

    @ApiOperation("查询章节数据")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return chapter == null ? Result.fail("查询章节数据失败", null) : Result.success("查询章节数据成功", chapter);
    }

    @ApiOperation("查询课程章节数据")
    @GetMapping("list/{courseId}")
    public Result listChaptersOfCourse(@ApiParam(name = "courseId", value = "课程 ID") @PathVariable Long courseId) {
        // 查询课程下的所有章节
        List<Chapter> chapters = chapterService.listChaptersOfCourse(courseId);
        return chapters.isEmpty() ? Result.fail("查询课程章节数据失败", null) : Result.success("查询课程章节数据成功", chapters);
    }
}

