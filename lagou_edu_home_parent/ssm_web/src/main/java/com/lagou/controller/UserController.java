package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 多条件分页查询用户信息
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {
        PageInfo<User> info = userService.findAllUserByPage(userVO);
        ResponseResult result = new ResponseResult(true,200,"响应成功",info);
        return result;
    }

    /**
     * 更新用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id,@RequestParam String status) {
        if(status.equalsIgnoreCase("ENABLE")) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",status);
        return result;
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        //获取用户信息
        User logUser = userService.login(user);
        //判断用户是否存在
        if (logUser != null) {
            //有用户资料
            //保存access_token信息
            Map<String ,Object> map = new HashMap<>();
            String token = UUID.randomUUID().toString();
            map.put("access_token",token);
            //保存用户id
            map.put("user_id",logUser.getId());
            HttpSession session = request.getSession();
            session.setAttribute("user_id",logUser.getId());
            session.setAttribute("access_token",token);
            ResponseResult result = new ResponseResult(true,1,"响应成功",map);
            return result;
        } else {
            ResponseResult result = new ResponseResult(true,1,"用户名密码错误",null);
            return result;
        }
    }

    /**
     * 获取当前用户拥有的角色
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id) {
        List<Role> list = userService.findUserRelationRoleById(id);
        ResponseResult result = new ResponseResult(true,200,"分配角色回显成功",list);
        return result;
    }

    /**
     * 用户分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {
        userService.userContextRole(userVO);
        ResponseResult result = new ResponseResult(true,200,"角色分配成功",null);
        return result;
    }

    /**
     * 获取用户权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        //获取请求头中的token
        String token = request.getHeader("Authorization");
        //获取登录时保存在Session中的token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        //判断token是否相等
        if (token.equals(access_token)) {
            //获取session中的用户id
            Integer userId = (Integer) session.getAttribute("user_id");
            //获取用户所分配的权限信息
            ResponseResult result = userService.getUserPermissions(userId);
            return result;
        } else {
            ResponseResult result = new ResponseResult(true,400,"获取失败",null);
            return result;
        }
    }

}
