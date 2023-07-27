package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
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
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO) {
        PageInfo<PromotionAd> info = promotionAdService.findAllAdByPage(promotionAdVO);
        ResponseResult result = new ResponseResult(true,200,"响应成功",info);
        return result;
    }

    /**
     * 图片上传
     */
    @RequestMapping("/PromotionAdUpload")
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
     * 保存与修改广告信息
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true,200,"保存成功",null);
            return result;
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true,200,"修改成功",null);
            return result;
        }
    }

    /**
     * 回显广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",promotionAd);
        return result;
    }

    /**
     * 广告位置上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id, @RequestParam int status) {
        if (status == 1) {
            promotionAdService.updatePromotionAdStatus(id,status);
        } else {
            promotionAdService.updatePromotionAdStatus(id,0);
        }
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }

}
