package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
public enum CaseStatusEnum {

    WAIT_FINISH(0, "待完成"), SUCCESS(1, "通过"), NOT_SUCCESS(2, "不通过");

    @Getter
    private int stateCode;
    @Getter
    private String stateDesc;

    CaseStatusEnum(int stateCode, String stateDesc) {
        this.stateCode = stateCode;
        this.stateDesc = stateDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static CaseStatusEnum getByCode(int code) {
        CaseStatusEnum[] enums = CaseStatusEnum.values();
        for (CaseStatusEnum stateEnum : enums) {
            if (stateEnum.stateCode == code) {
                return stateEnum;
            }
        }
        return null;
    }

    public static CaseStatusEnum getByDesc(String stateDesc) {
        CaseStatusEnum[] enums = CaseStatusEnum.values();
        for (CaseStatusEnum stateEnum : enums) {
            if (stateEnum.stateDesc.equals(stateDesc)) {
                return stateEnum;
            }
        }
        return null;
    }
}
