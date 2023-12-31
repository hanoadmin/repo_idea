package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源分类信息
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询资源分类信息
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }
}
