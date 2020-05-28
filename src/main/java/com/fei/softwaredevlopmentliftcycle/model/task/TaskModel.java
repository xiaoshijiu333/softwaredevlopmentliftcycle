package com.fei.softwaredevlopmentliftcycle.model.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
@Getter
@Setter
public class TaskModel {

    private Integer id;

    private String taskName;

    private Integer devId;

    private String token;

    private String devName;

    private String taskDesc;

    private Integer projectId;

    private Integer preCost;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;

    private Date endTime;

}
