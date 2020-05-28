package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/19
 * @Description: $value$
 */
@Setter
@Getter
@Table(name = "deploy")
public class Deploy {

    public static final String CASE_ID = "id";

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String DEPLOY_DOMIN = "deployDomin";
    private String deployDomin;

    public static final String DEPLOY_SERVER_SITE = "deployServerSite";
    private String deployServerSite;

    public static final String DEPLOY_DATABASE_SITE = "deployDatabaseSite";
    private String deployDatabaseSite;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;
}
