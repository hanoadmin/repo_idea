package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuRelation;

import java.util.List;

/**
 * 角色Mapper
 */
public interface RoleMapper {

    /**
     * 多条件查询角色信息
     */
    public List<Role> findAllRole(Role role);

    /**
     * 添加角色信息
     */
    public void saveRole(Role role);

    /**
     * 修改角色信息
     */
    public void updateRole(Role role);

    /**
     * 根据角色信息查询菜单关联菜单
     */
    public List<String> findMenuByRoleId(int roleId);

    /**
     * 删除角色关联的菜单信息
     */
    public void deleteRoleContextMenu(int roleId);

    /**
     * 角色菜单关联
     */
    public void roleContextMenu(RoleMenuRelation roleMenuRelation);

    /**
     * 删除角色信息
     */
    public void deleteRole(int roleId);

}
