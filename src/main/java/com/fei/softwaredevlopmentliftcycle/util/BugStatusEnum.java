package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
public enum BugStatusEnum {

    待完成(0, "待完成"), 待检查(1, "待检查"), 不通过(2, "不通过"), 关闭(3, "关闭"), 挂起(4, "挂起");

    @Getter
    private int stateCode;
    @Getter
    private String stateDesc;

    BugStatusEnum(int stateCode, String stateDesc) {
        this.stateCode = stateCode;
        this.stateDesc = stateDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static BugStatusEnum getByCode(int code) {
        BugStatusEnum[] enums = BugStatusEnum.values();
        for (BugStatusEnum stateEnum : enums) {
            if (stateEnum.stateCode == code) {
                return stateEnum;
            }
        }
        return null;
    }

    public static BugStatusEnum getByDesc(String stateDesc) {
        BugStatusEnum[] enums = BugStatusEnum.values();
        for (BugStatusEnum stateEnum : enums) {
            if (stateEnum.stateDesc.equals(stateDesc)) {
                return stateEnum;
            }
        }
        return null;
    }
}
