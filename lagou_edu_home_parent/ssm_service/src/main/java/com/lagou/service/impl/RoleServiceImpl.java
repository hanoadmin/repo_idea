package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuRelation;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 角色列表Service
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 多条件查询角色信息
     */
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    /**
     * 保存角色信息
     */
    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    /**
     * 修改角色信息
     */
    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        role.setUpdatedBy("system");
        roleMapper.updateRole(role);
    }

    /**
     * 根据角色信息查询关联菜单信息
     */
    public List<String> findMenuByRoleId(int roleId) {
        List<String> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    /**
     * 角色菜单关联
     */
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {
        //清楚角色菜单中间表
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());
        //关联角色菜单
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleId(roleMenuVO.getRoleId());
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setCreatedTime(new Date());
            roleMenuRelation.setUpdatedTime(new Date());
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");
            roleMapper.roleContextMenu(roleMenuRelation);
        }
    }

    /**
     * 删除角色信息
     */
    @Override
    public void deleteRole(int roleId) {
        //删除角色关联的菜单信息中间表
        roleMapper.deleteRoleContextMenu(roleId);
        //删除角色
        roleMapper.deleteRole(roleId);
    }


}
