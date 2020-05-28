package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.data.Role;
import com.fei.softwaredevlopmentliftcycle.model.role.RoleModel;
import com.fei.softwaredevlopmentliftcycle.model.user.CreateUserModel;
import com.fei.softwaredevlopmentliftcycle.model.user.UserModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: $value$
 */
public interface UserService {

    /**
     * 根据Token获取用户信息，手机号查询（后期，维护到Redis中）
     * @param token token值，也就是手机号
     * @return UserModel 用户信息
     */
    UserModel userInfo(String token);

    /**
     * @param phone 手机号
     * @param password 密码
     * @return token
     */
    String login(String phone, String password);

    /**
     * 查询所有的用户
     * @param status
     */
    List<UserModel> all(String status);

    List<UserModel> getProUser(Integer pid, String roleName);

    void delete(Integer uid);

    List<Role> roles();

    void editRole(RoleModel roleModel);

    Integer create(CreateUserModel userModel);
}
