package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuRelation;
import com.lagou.domain.RoleMenuVO;

import java.util.List;

/**
 * 角色列表Service
 */
public interface RoleService {

    /**
     * 多条件查询角色信息
     */
    public List<Role> findAllRole(Role role);

    /**
     * 保存角色信息
     */
    public void saveRole(Role role);

    /**
     * 修改角色信息
     */
    public void updateRole(Role role);

    /**
     * 根据角色信息查询关联菜单信息
     */
    public List<String> findMenuByRoleId(int roleId);

    /**
     * 角色菜单关联
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 删除角色信息
     */
    public void deleteRole(int roleId);
}
