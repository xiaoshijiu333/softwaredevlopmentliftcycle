package com.fei.softwaredevlopmentliftcycle.model.bug;

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
@Setter
@Getter
public class BugModel {

    private Integer id;

    private String bugName;

    private String bugDesc;

    private String bugStatus;

    private String testName;

    private Integer projectId;
    private String projectName;

    private String devName;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;
}
