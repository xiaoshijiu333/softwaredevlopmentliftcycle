package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: 项目状态枚举
 */
public enum StateEnum {
    需求分析阶段(0, "需求分析阶段"), 研发阶段(1, "研发阶段"), 测试阶段(2, "测试阶段"), 发布阶段(3, "发布阶段"), 已上线(4, "已上线");

    @Getter
    private int stateCode;
    @Getter
    private String stateDesc;

    StateEnum(int stateCode, String stateDesc) {
        this.stateCode = stateCode;
        this.stateDesc = stateDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static StateEnum getByCode(int code) {
        StateEnum[] enums = StateEnum.values();
        for (StateEnum stateEnum : enums) {
            if (stateEnum.stateCode == code) {
                return stateEnum;
            }
        }
        return null;
    }

    public static StateEnum getByDesc(String stateDesc) {
        StateEnum[] enums = StateEnum.values();
        for (StateEnum stateEnum : enums) {
            if (stateEnum.stateDesc.equals(stateDesc)) {
                return stateEnum;
            }
        }
        return null;
    }
}
