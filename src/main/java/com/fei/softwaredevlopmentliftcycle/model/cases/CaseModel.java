package com.fei.softwaredevlopmentliftcycle.model.cases;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Getter
@Setter
public class CaseModel {
    private Integer id;

    private String caseName;

    private String testName;

    private Integer preCost;

    private String devName;

    private String caseStatus;

    private String caseDesc;

    private Integer projectId;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;
}
