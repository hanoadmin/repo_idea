package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * 资源分类Mapper
 */
public interface ResourceCategoryMapper {

    /**
     * 查询资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();


}
