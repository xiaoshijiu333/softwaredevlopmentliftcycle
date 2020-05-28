package com.fei.softwaredevlopmentliftcycle.model.deploy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/19
 * @Description: $value$
 */
@Setter
@Getter
public class DeployModel {

    private Integer id;

    private String deployDomin;

    private String deployServerSite;

    private String deployDatabaseSite;

    private Integer projectId;

    private String projectName;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;
}
