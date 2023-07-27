package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /**
     * 查询广告位信息
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 新增广告位信息
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位信息
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据ID查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id);

}
