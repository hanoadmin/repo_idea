package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 资源列表Service
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 多条件分页查询资源列表信息
     */
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> list = resourceMapper.findAllResource(resourceVo);
        PageInfo<Resource> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 添加资源信息
     */
    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }

    /**
     * 修改资源信息
     */
    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system");
        resourceMapper.updateResource(resource);
    }

    /**
     * 删除资源信息
     */
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }

}
