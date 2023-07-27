package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    /**
     * 查询课程下的章节与课时信息
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return list;
    }

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /**
     * 保存章节信息
     */
    @Override
    public void saveSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    /**
     * 修改章节信息
     */
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSection(courseSection);
    }

    /**
     * 修改章节状态
     */
    @Override
    public void updateSectionStatus(int id,int status) {
        //封装章节信息
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);
    }

    /**
     * 保存课时信息
     */
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseLesson.setIsDel(0);
        courseLesson.setStatus(2);
        courseContentMapper.saveLesson(courseLesson);
    }


}
