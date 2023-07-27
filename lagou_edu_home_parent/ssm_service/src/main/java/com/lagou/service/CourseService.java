package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseService {

    /**
     * 根据课程名称、课程状态查询课程信息
     */
    public List<Course> findCourseByConditioin(CourseVO courseVO);

    /**
     * 新增课程信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO);

    /**
     * 根据id查询课程信息
     */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO);

    /**
     * 更新课程状态
     */
    public void updateCourseStatus(int id,int status);

}
