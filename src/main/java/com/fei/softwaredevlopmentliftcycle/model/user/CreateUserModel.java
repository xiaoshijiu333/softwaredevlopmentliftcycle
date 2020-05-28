package com.fei.softwaredevlopmentliftcycle.model.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/17
 * @Description: $value$
 */
@Setter
@Getter
public class CreateUserModel {
    private String username;

    private String phone;

    private Integer roleId;

    private String roleName;

}
