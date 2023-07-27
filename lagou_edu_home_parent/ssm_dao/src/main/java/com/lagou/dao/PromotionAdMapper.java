package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页查询广告信息
     */
    public List<PromotionAd> findAllAdByPage();

    /**
     * 保存广告信息
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告信息
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据ID查询广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 更新广告状态
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
