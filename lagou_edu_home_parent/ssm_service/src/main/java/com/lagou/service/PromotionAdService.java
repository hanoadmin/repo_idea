package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /**
     * 分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVO promotionAdVO);

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
    public void updatePromotionAdStatus(int id,int status);
}
