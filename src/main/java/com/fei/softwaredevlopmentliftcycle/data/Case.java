package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Getter
@Setter
@Table(name = "testcase")
public class Case {

    public static final String CASE_ID = "id";
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String CASE_NAME = "caseName";
    private String caseName;

    public static final String TEST_NAME = "testName";
    private String testName;

    public static final String DEV_NAME = "devName";
    private String devName;

    public static final String CASE_DESC = "caseDesc";
    private String caseDesc;

    public static final String PRE_COST = "preCost";
    private Integer preCost;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String CASE_STATUS = "testStatus";
    private Integer caseStatus;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String FINISH_TIME = "finishTime";
    private Date finishTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;

}
