package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

/**
 * 权限菜单
 */
public interface MenuMapper {

    /**
     * 查询父子级菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     */
    public List<Menu> findAllMenu();

    /**
     * 根据id查询菜单信息
     */
    public Menu findMenuById(int id);

    /**
     * 添加菜单信息
     */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单信息
     */
    public void updateMenu(Menu menu);

}
