package com.fei.softwaredevlopmentliftcycle.model.cases;

import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Setter
@Getter
public class WebCaseModel {

    private Integer id;

    private String caseName;

    private String testName;

    private String caseStatus;

    private Integer preCost;

    private String devName;

    private String caseDesc;

    private Integer projectId;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT1)
    private Date createTime;

}
