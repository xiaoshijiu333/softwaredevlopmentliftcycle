package com.fei.softwaredevlopmentliftcycle.model.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fei.common.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/13
 * @Description: $value$
 */
@Setter
@Getter
public class CommentModel {

    private Integer id;

    private String commentContext;

    private Integer userId;

    private String username;

    private String headPhoto;

    private Integer projectId;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT1, timezone = DateUtil.TIME_ZONE)
    private Date createTime;

}
