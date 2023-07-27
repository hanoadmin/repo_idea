package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源列表Controller
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 多条件分页查询资源列表信息
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功",pageInfo);
        return result;
    }

    /**
     * 添加OR修改资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        if(resource.getId() == null) {
            resourceService.saveResource(resource);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        } else {
            resourceService.updateResource(resource);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }
    }

    /**
     * 删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(int id) {
        resourceService.deleteResource(id);
        ResponseResult result = new ResponseResult(true,200,"删除成功",null);
        return result;
    }

}
