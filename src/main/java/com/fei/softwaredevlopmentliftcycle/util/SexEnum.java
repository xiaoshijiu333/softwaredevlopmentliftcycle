package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: 性别枚举
 */
public enum SexEnum {

    MAN(0, "男"), WOMEN(1, "女"), UNKNOW(2, "未知");

    @Getter
    private int sexCode;
    @Getter
    private String sexDesc;

    SexEnum(int sexCode, String sexDesc) {
        this.sexCode = sexCode;
        this.sexDesc = sexDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static SexEnum getByCode(int code) {
        SexEnum[] enums = SexEnum.values();
        for (SexEnum sexEnum : enums) {
            if (sexEnum.sexCode == code) {
                return sexEnum;
            }
        }
        return null;
    }
}
