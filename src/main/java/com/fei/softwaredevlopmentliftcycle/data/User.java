package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: User类
 */
@Setter
@Getter
@Table(name = "user")
public class User {

    /**
     * 定义常量字段
     * @keysql：插入返回主键
     */
    public static final String USER_ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String PASSWORD = "password";
    private String password;

    public static final String USER_NAME = "username";
    private String username;

    public static final String USER_PHONE = "phone";
    private String phone;

    public static final String USER_SEX = "sex";
    private Integer sex;

    public static final String HEAD_PHOTO="headPhoto";
    private String headPhoto;

    public static final String CREATE_TIME="createTime";
    private Date createTime;

    public static final String DELETE_TIME="deleteTime";
    private String deleteTime;

    private List<Role> userRoles;
}
