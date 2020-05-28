package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.alibaba.fastjson.JSON;
import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.data.Role;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.data.UserProject;
import com.fei.softwaredevlopmentliftcycle.data.UserRole;
import com.fei.softwaredevlopmentliftcycle.mapper.RoleMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserProjectMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserRoleMapper;
import com.fei.softwaredevlopmentliftcycle.model.role.RoleModel;
import com.fei.softwaredevlopmentliftcycle.model.user.CreateUserModel;
import com.fei.softwaredevlopmentliftcycle.model.user.UserModel;
import com.fei.softwaredevlopmentliftcycle.service.UserService;
import com.fei.softwaredevlopmentliftcycle.util.RoleEnum;
import com.fei.softwaredevlopmentliftcycle.util.SexEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: User Service层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserModel userInfo(String token) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.USER_PHONE, token);
        List<User> users = userMapper.selectByExample(example);
        UserModel userModel = null;
        if (!CollectionUtils.isEmpty(users)) {
            User user = users.get(0);
            // 再去查一下用户角色关联表，查出该用户拥有的角色
            Example r = new Example(UserRole.class);
            r.createCriteria().andEqualTo(UserRole.USER_ID, user.getId())
                    .andEqualTo(User.DELETE_TIME, "");
            List<UserRole> userRoles = userRoleMapper.selectByExample(r);
            userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel, UserModel.class);
            userModel.setSex(Optional.ofNullable(SexEnum.getByCode(user.getSex()))
                    .map(SexEnum::getSexDesc).orElse("未知"));
            // 角色处理
            userModel.setUserRoles(
                    userRoles.stream().map(UserRole::getRoleName).collect(Collectors.toList()));
        }
        return userModel;

    }

    @Override
    public String login(String phone, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.USER_PHONE, phone).andEqualTo(User.PASSWORD,
                password);
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0).getPhone();
        }
        return null;
    }

    @Override
    public List<UserModel> all(String status) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            List<UserModel> userModels = new ArrayList<>();
            users.forEach(u -> {
                UserModel userModel = new UserModel();
                BeanUtils.copyProperties(u, userModel, UserModel.class);
                userModel.setSex(SexEnum.getByCode(u.getSex()).getSexDesc());
                Example example1 = new Example(UserRole.class);
                example1.createCriteria().andEqualTo(UserRole.USER_ID, u.getId())
                        .andEqualTo(UserRole.DELETE_TIME, "");
                List<UserRole> userRoles = userRoleMapper.selectByExample(example1);
                List<String> list = userRoles.stream().map(UserRole::getRoleName)
                        .collect(Collectors.toList());
                // 查具体的某个角色的所有人员
                if (!StringUtils.isEmpty(status) && list.get(0).equals(status)) {
                    // 角色处理
                    userModel.setUserRoles(list.stream().map(s -> RoleEnum.getByCode(s).getCDesc())
                            .collect(Collectors.toList()));
                    userModels.add(userModel);
                }
                // 排除superadmin，不把它查出来
                if (StringUtils.isEmpty(status)) {
                    if (list.size() != 1 || !list.get(0).equals("superadmin")) {
                        // 角色处理
                        userModel.setUserRoles(
                                list.stream().map(s -> RoleEnum.getByCode(s).getCDesc())
                                        .collect(Collectors.toList()));
                        userModels.add(userModel);
                    }
                }
            });
            return userModels;
        }
        return null;
    }

    @Override
    public List<UserModel> getProUser(Integer pid, String roleName) {
        Example example = new Example(UserProject.class);
        example.createCriteria().andEqualTo(UserProject.PROJECT_ID, pid)
                .andEqualTo(UserProject.DELETE_TIME, "");
        List<UserProject> userProjects = userProjectMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userProjects)) {
            Example example1 = new Example(UserRole.class);
            example1.createCriteria().andEqualTo(UserRole.ROLE_NAME, roleName)
                    .andIn(UserRole.USER_ID,
                            userProjects.stream().map(UserProject::getUserId)
                                    .collect(Collectors.toList()))
                    .andEqualTo(UserRole.DELETE_TIME, "");
            List<UserRole> userRoles = userRoleMapper.selectByExample(example1);
            if (!CollectionUtils.isEmpty(userRoles)) {
                Example example2 = new Example(User.class);
                example2.createCriteria().andEqualTo(User.DELETE_TIME, "").andIn(User.USER_ID,
                        userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList()));
                List<User> users = userMapper.selectByExample(example2);
                List<UserModel> userModels = JSON.parseArray(JSON.toJSONString(users),
                        UserModel.class);
                return userModels;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setDeleteTime(DateUtil.getDeleteTimeStr());
        userMapper.updateByPrimaryKeySelective(user);
        Example example = new Example(UserRole.class);
        UserRole userRole = new UserRole();
        userRole.setDeleteTime(DateUtil.getDeleteTimeStr());
        example.createCriteria().andEqualTo(UserRole.USER_ID, uid).andEqualTo(UserRole.DELETE_TIME,
                "");
        userRoleMapper.updateByExampleSelective(userRole, example);
    }

    @Override
    public List<Role> roles() {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo(Role.DELETE_TIME, "").andNotEqualTo(Role.ROLE_NAME,
                "superadmin");
        List<Role> roles = roleMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(roles)) {
            return roles.stream().map(role -> {
                role.setRoleName(RoleEnum.getByCode(role.getRoleName()).getCDesc());
                return role;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void editRole(RoleModel roleModel) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleModel.getId());
        userRole.setRoleName(RoleEnum.getBycDesc(roleModel.getRoleName()).getEDesc());
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo(UserRole.USER_ID, roleModel.getUid());
        userRoleMapper.updateByExampleSelective(userRole, example);
    }

    @Override
    public Integer create(CreateUserModel userModel) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setPassword("123456");
        user.setUsername(userModel.getUsername());
        user.setPhone(userModel.getPhone());
        user.setHeadPhoto("e.jpg");
        userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(userModel.getRoleId());
        userRole.setRoleName(RoleEnum.getBycDesc(userModel.getRoleName()).getEDesc());
        userRole.setCreateTime(new Date());
        userRoleMapper.insertSelective(userRole);
        return user.getId();
    }

}
