package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色列表Controller
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    //角色信息
    @Autowired
    private RoleService roleService;
    //菜单信息
    @Autowired
    private MenuService menuService;

    /**
     * 条件查询角色信息
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> roleList = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true,200,"响应成功",roleList);
        return result;
    }

    /**
     * 保存OR修改角色信息
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
            ResponseResult result = new ResponseResult(true,200,"保存成功",null);
            return result;
        } else {
            roleService.updateRole(role);
            ResponseResult result = new ResponseResult(true,200,"修改成功",null);
            return result;
        }
    }

    /**
     * 查询父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }

    /**
     * 根据角色信息查询关联菜单信息
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId) {
        List<String> list = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }

    /**
     * 角色菜单关联
     */
    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {
        roleService.roleContextMenu(roleMenuVO);
        ResponseResult result = new ResponseResult(true,200,"响应成功",null);
        return result;
    }

    /**
     * 删除角色信息
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id) {
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",null);
        return result;
    }
}
