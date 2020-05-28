package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
@Setter
@Getter
@Table(name = "task")
public class Task {

    public static final String TASK_ID = "id";

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String TASK_NAME = "taskName";
    private String taskName;

    public static final String DEV_ID = "devId";
    private Integer devId;

    public static final String TASK_DESC = "taskDesc";
    private String taskDesc;

    public static final String DEV_NAME = "devName";
    private String devName;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String PRE_COST = "preCost";
    private Integer preCost;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String END_TIME = "endTime";
    private Date endTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
