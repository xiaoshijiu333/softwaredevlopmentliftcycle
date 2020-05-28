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
 * @Description: 项目实体
 */
@Setter
@Getter
@Table(name = "project")
public class Project {

    public static final String PROJECT_ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String PROJECT_NAME = "projectName";
    private String projectName;

    public static final String PROJECT_STATE = "projectState";
    private Integer projectState;

    public static final String PROJECT_DESC = "projectDesc";
    private String projectDesc;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
