package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 菜单列表
 */
public interface MenuService {

    /**
     * 查询父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     */
    public PageInfo<Menu> findAllMenu(int currentPage,int pageSize);

    /**
     * 根据id查询菜单信息
     */
    public Menu findMenuById(int id);

    /**
     * 保存菜单信息
     */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单信息
     */
    public void updateMenu(Menu menu);

}
