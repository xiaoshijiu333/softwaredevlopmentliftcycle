package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: 性别枚举
 */
public enum MessageEnum {

    未读(0, "未读"), 已读(1, "已读");

    @Getter
    private int msgCode;
    @Getter
    private String msgDesc;

    MessageEnum(int msgCode, String msgDesc) {
        this.msgCode = msgCode;
        this.msgDesc = msgDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static MessageEnum getByCode(int code) {
        MessageEnum[] enums = MessageEnum.values();
        for (MessageEnum msgEnum : enums) {
            if (msgEnum.msgCode == code) {
                return msgEnum;
            }
        }
        return null;
    }
}
