package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 查询广告位信息
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }

    /**
     * 新增与修改广告位信息
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        if (promotionSpace.getId() == null) {
            //封装广告位信息
            UUID uid = UUID.randomUUID();
            promotionSpace.setSpaceKey(uid.toString());
            promotionSpace.setCreateTime(new Date());
            promotionSpace.setUpdateTime(new Date());
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true,200,"新增成功",null);
            return result;
        } else {
            //封装广告位信息
            promotionSpace.setUpdateTime(new Date());
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true,200,"修改成功",null);
            return result;
        }
    }

    /**
     * 回显广告位信息
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",promotionSpace);
        return result;
    }

}
