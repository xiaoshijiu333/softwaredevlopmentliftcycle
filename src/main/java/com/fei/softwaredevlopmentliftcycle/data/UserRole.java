package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/19
 * @Description: 用户关联角色表
 */
@Setter
@Getter
@Table(name = "user_role")
public class UserRole {

    /**
     * 定义常量字段
     * @keysql：插入返回主键
     */
    public static final String ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String USER_ID = "userId";
    private Integer userId;

    public static final String ROLE_ID = "roleId";
    private Integer roleId;

    public static final String ROLE_NAME = "roleName";
    private String roleName;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
