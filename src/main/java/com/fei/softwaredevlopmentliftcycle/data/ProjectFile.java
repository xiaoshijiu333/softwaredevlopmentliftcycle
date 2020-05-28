package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/24
 * @Description: $value$
 */
@Setter
@Getter
@Table(name = "project_file")
public class ProjectFile {

    public static final String ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String FILE_URL = "url";
    private String url;

    public static final String FILE_NAME = "name";
    private String name;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;
}
