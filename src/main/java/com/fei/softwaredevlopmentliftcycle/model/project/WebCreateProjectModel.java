package com.fei.softwaredevlopmentliftcycle.model.project;

import com.fei.softwaredevlopmentliftcycle.model.file.WebFileModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/22
 * @Description: 创建项目接受模型
 */
@Setter
@Getter
public class WebCreateProjectModel implements Serializable {

    private Integer id;

    private static final long serialVersionUID = -7164659255886729970L;
    private String projectName;

    private List<Integer> projectPeopleIds;

    private String projectDesc;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<WebFileModel> projectFileUrls;
}
