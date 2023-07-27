package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 根据课程名称、课程状态查询课程信息
     */
    @Override
    public List<Course> findCourseByConditioin(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByConditioin(courseVO);
        return list;
    }

    /**
     * 保存课程信息
     */
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);
            //补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            //保存课程信息
            courseMapper.saveCourse(course);
            //获取插入数据的courseId
            int courseId = course.getId();
            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);
            //补全讲师信息
            teacher.setCourseId(courseId);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            //保存讲师信息
            courseMapper.saveTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询课程信息
     */
    public CourseVO findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    /**
     * 修改课程信息与讲师信息
     * @param courseVO
     */
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);
            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);
            courseMapper.updateCourse(course);
            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);
            teacher.setUpdateTime(date);
            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新课程状态
     * @param id
     * @param status
     */
    @Override
    public void updateCourseStatus(int id, int status) {
        //封装course
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }


}
