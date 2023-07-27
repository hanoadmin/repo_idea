package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有用户
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 更新用户状态
     */
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /**
     * 用户登录
     */
    public User login(User user);

    /**
     * 清空用户关联角色的中间表
     */
    public void deleteUserContextRole(int id);

    /**
     * 用户角色关联
     */
    public void userContextRole(UserRoleRelation userRoleRelation);

    /**
     * 根据用户ID查询当前角色
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 根据用户分配的角色ID查询角色拥有的顶级菜单信息
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    /**
     * 根据父级菜单信息查询子级菜单信息
     */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

    public List<Resource> findResourceByRoleId2(List<Integer> ids);

}
