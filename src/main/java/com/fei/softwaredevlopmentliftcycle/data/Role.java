package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/19
 * @Description: 角色
 */
@Setter
@Getter
public class Role {

    /**
     * 定义常量字段
     * @keysql：插入返回主键
     */
    public static final String ROLE_ID = "id";
    private Integer id;

    public static final String ROLE_NAME = "roleName";
    private String roleName;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
