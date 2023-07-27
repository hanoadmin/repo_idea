package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * 资源分类Service
 */
public interface ResourceCategoryService {

    /**
     * 查询资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();

}
