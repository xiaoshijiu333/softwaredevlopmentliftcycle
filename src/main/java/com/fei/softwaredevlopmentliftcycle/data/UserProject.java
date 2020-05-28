package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: 用户关联项目表实体
 */
@Setter
@Getter
@Table(name = "user_project")
public class UserProject {

    public static final String ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String USER_ID = "userId";
    private Integer userId;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
