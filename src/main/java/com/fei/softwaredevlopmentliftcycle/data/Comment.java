package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/13
 * @Description: $value$
 */
@Setter
@Getter
@Table(name = "comment")
public class Comment {

    public static final String COMMENT_ID = "id";

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String COMMENT_CONTEXT = "commentContext";
    private String commentContext;

    public static final String USER_ID = "userId";
    private Integer userId;

    public static final String PROJECT_ID = "projectId";
    private Integer projectId;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;
}
