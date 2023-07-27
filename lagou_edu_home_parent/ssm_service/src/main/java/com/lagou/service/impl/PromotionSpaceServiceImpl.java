package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /**
     * 查询广告位信息
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    /**
     * 新增广告位信息
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /**
     * 修改广告位信息
     */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    /**
     * 根据ID查询广告位信息
     */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }
}
