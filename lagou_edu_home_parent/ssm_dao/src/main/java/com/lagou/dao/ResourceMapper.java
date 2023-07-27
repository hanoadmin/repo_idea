package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;


import java.util.List;

/**
 * 资源列表Mapper
 */
public interface ResourceMapper {

    /**
     * 多条件分页查询资源列表信息
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);

    /**
     * 添加资源信息
     */
    public void saveResource(Resource resource);

    /**
     * 修改资源信息
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源信息
     */
    public void deleteResource(int id);

}
