package com.fei.softwaredevlopmentliftcycle.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: User 模型（返回模型和转换模型公用）
 */
@Setter
@Getter
public class UserModel implements Serializable {

    private static final long serialVersionUID = 8427547417511962076L;
    private Integer id;

    private String username;

    private String phone;

    private String sex;

    private String headPhoto;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;

    private String deleteTime;

    private List<String> userRoles;
}
