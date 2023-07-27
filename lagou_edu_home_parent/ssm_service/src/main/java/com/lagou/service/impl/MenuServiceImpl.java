package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 权限菜单信息
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询父子菜单信息
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    /**
     * 查询菜单列表
     */
    @Override
    public PageInfo<Menu> findAllMenu(int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Menu> list = menuMapper.findAllMenu();
        PageInfo<Menu> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 根据ID查询菜单信息
     */
    @Override
    public Menu findMenuById(int id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    /**
     * 保存菜单信息
     */
    @Override
    public void saveMenu(Menu menu) {
        //补全信息
        menu.setCreatedTime(new Date());
        menu.setCreatedBy("system");
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    /**
     * 修改菜单信息
     */
    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }
}
