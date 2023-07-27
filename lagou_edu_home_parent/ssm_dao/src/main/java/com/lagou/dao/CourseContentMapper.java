package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /**
     * 查询课程下的章节与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 回显章节对应的课程信息
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节信息
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(CourseSection courseSection);

    /**
     * 保存课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

}
