package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: 项目状态枚举
 */
public enum StateEnum {
    XUQIUFENXI(0, "需求分析阶段"), YANFA(1, "研发阶段"), CESHI(2, "测试阶段"), FABU(3, "发布阶段"), SHANGXIAN(4, "已上线");

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
