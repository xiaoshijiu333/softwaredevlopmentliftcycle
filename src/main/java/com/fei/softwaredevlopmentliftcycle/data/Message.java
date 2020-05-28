package com.fei.softwaredevlopmentliftcycle.data;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
@Setter
@Getter
@Table(name = "message")
public class Message {

    public static final String CASE_ID = "id";

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    public static final String MESSAGE_TITLE = "messageTitle";
    private String messageTitle;

    public static final String MESSAGE_STATUE = "messageStatus";
    private Integer messageStatus;

    public static final String RECEIVE_ID = "receiveId";
    private Integer receiveId;

    public static final String CREATE_TIME = "createTime";
    private Date createTime;

    public static final String DELETE_TIME = "deleteTime";
    private String deleteTime;
}
