package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 多条件分页查询用户信息
     */
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVO);
        PageInfo<User> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 更新用户状态
     */
    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    /**
     * 用户登录
     */
    @Override
    public User login(User user) throws Exception{
        User logUser = userMapper.login(user);
        if (logUser != null && Md5.verify(user.getPassword(),Md5.md5key,logUser.getPassword())) {
            return logUser;
        }
        return null;
    }

    /**
     * 根据用户ID查询当前角色
     */
    @Override
    public List<Role> findUserRelationRoleById(int id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    /**
     * 用户分配角色信息
     */
    @Override
    public void userContextRole(UserVO userVO) {
        //根据用户id清除中间表的关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());
        //向中间表添加用户的角色信息
        for (Integer roleId : userVO.getRoleIdList()) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userVO.getUserId());
            userRoleRelation.setRoleId(roleId);
            userRoleRelation.setCreatedTime(new Date());
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedTime(new Date());
            userRoleRelation.setUpdatedby("system");
            userMapper.userContextRole(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //获取当前用户的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        //封装角色id
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //根据角色信息获取顶级菜单信息
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);
        //封装顶级菜单ID
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //获取资源权限
        List<Resource> resList = userMapper.findResourceByRoleId(roleIds);
        //封装响应体
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resList);
        ResponseResult result = new ResponseResult(true,1,"success",map);
        return result;
    }
}
