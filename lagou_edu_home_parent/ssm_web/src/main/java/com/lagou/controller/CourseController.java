package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 多条件查询
     */
    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVO courseVO) {
        //根据课程名称或课程状态查询课程信息
        List<Course> list = courseService.findCourseByConditioin(courseVO);
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(MultipartFile file, HttpServletRequest request) {
        ResponseResult result = null;
        try {
            //判断接受到的上传文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //获取项目部署路径
            String realPath = request.getServletContext().getRealPath("/");
            //截取路径地址
            String subString = realPath.substring(0,realPath.indexOf("ssm_web"));
            //获取上传文件的名称
            String fileName = file.getOriginalFilename();
            //生成新的文件名称
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
            //定义文件上传路径
            String uploadPath = subString+"upload\\";
            //构建新的文件
            File filePath = new File(uploadPath,newFileName);
            //验证文件上传路径是否存在,不存在则创建
            if(!filePath.getParentFile().exists()) {
                //创建文件
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" + filePath);
            }
            //上传文件
            file.transferTo(filePath);
            //将文件名与文件路径返回,并响应
            Map<String,String> map = new HashMap<String,String>();
            map.put("fileName",newFileName);
            map.put("filePath","http://localhost:8080/upload/"+newFileName);
            //封装响应数据并返回
            result = new ResponseResult(true,200,"图片上传成功",map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 保存课程信息与更新课程信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) {
        if (courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
            return result;
        } else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }
    }

    /**
     * 根据id查询课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id) {
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",courseVO);
        return result;
    }

    /**
     * 更新课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id,@RequestParam int status) {
        //更新状态
        courseService.updateCourseStatus(id,status);
        //保存修改后的状态并保存
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }

}
