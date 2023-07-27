package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 查询课程下的章节与课时信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId) {
        //查询
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        //封装ResponseResult
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }

    /**
     * 回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId) {
        //查询课程信息
        Course course = courseContentService.findCourseByCourseId(courseId);
        //封装ResponseResult
        ResponseResult result = new ResponseResult(true,200,"响应成功",course);
        return result;
    }

    /**
     * 保存章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        } else {
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id,@RequestParam int status) {
        courseContentService.updateSectionStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }

    /**
     * 保存课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson) {
        courseContentService.saveLesson(courseLesson);
        return new ResponseResult(true,200,"响应成功",courseLesson);
    }
}
