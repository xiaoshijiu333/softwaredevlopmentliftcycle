package com.fei.softwaredevlopmentliftcycle.model.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
@Setter
@Getter
public class NoticeMessageModel {

    private Integer id;

    private String messageTitle;

    private List<Integer> ids;

}
