package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 根据课程名称、课程状态查询课程信息
     */
    public List<Course> findCourseByConditioin(CourseVO courseVO);

    /**
     * 新增课程信息
     */
    public void saveCourse(Course course);

    /**
     * 新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据id查询课程信息
     */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     */
    public void updateCourse(Course course);

    /**
     * 修改讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 更新课程状态
     */
    public void updateCourseStatus(Course course);

    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

}
