package com.lagou.domain;

import java.util.List;

public class RoleMenuVO {

    //角色ID
    private int roleId;
    //菜单列表
    private List<Integer> menuIdList;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
