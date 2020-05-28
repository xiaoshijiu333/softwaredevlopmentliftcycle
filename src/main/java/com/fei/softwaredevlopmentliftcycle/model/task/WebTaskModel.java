package com.fei.softwaredevlopmentliftcycle.model.task;

import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
@Setter
@Getter
public class WebTaskModel {
    private Integer id;

    private Integer tid;

    private String taskName;

    private Integer devId;

    private String token;

    private String taskDesc;

    private Integer projectId;

    private Integer preCost;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT1)
    private Date createTime;

    private Date endTime;
}
