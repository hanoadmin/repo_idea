package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单列表
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(@RequestParam int currentPage,@RequestParam int pageSize) {
        PageInfo<Menu> page = menuService.findAllMenu(currentPage,pageSize);
        ResponseResult result = new ResponseResult(true,200,"响应成功",page);
        return result;
    }

    /**
     * 查询菜单信息回显
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id) {
        if (id == -1) {
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String,Object> map =  new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } else {
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }
    }

    /**
     * 保存OR修改菜单信息
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {
        if (menu.getId() == null) {
            menuService.saveMenu(menu);
            ResponseResult result = new ResponseResult(true,200,"保存成功",null);
            return result;
        } else {
            menuService.updateMenu(menu);
            ResponseResult result = new ResponseResult(true,200,"修改成功",null);
            return result;
        }
    }



}
