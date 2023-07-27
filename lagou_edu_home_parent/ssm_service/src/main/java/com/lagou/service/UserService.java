package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     */
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    /**
     * 更新用户状态
     */
    public void updateUserStatus(int id,String status);

    /**
     * 用户登录
     */
    public User login(User user) throws Exception;

    /**
     * 根据用户id查询当前角色
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 用户关联角色信息
     */
    public void userContextRole(UserVO userVO);

    /**
     * 获取用户权限
     */
    public ResponseResult getUserPermissions(Integer id);

}
