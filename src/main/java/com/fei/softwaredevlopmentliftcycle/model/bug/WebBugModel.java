package com.fei.softwaredevlopmentliftcycle.model.bug;

import java.util.Date;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Setter
@Getter
public class WebBugModel {

    private Integer id;

    private String bugName;

    private String bugDesc;

    private String bugStatus;

    private String testName;

    private Integer projectId;

    private String devName;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT1)
    private Date createTime;
}
