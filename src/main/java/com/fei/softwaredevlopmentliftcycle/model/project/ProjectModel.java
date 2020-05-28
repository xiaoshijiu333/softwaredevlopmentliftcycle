package com.fei.softwaredevlopmentliftcycle.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.model.file.WebFileModel;
import com.fei.softwaredevlopmentliftcycle.model.user.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: Project返回模型
 */
@Getter
@Setter
public class ProjectModel implements Serializable {

    private static final long serialVersionUID = 207626325155786507L;
    private String projectName;

    private Integer id;

    private String projectStateDesc;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;

    private String projectDesc;

    private Integer projectState;

    private List<Integer> projectPeopleIds;
    // name拼接
    private String projectPeople;

    private List<UserModel> projectPeoples;

    private List<WebFileModel> projectFileUrls;

}
