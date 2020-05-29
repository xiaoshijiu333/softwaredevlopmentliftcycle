package com.fei.softwaredevlopmentliftcycle.util;

import lombok.Getter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/22
 * @Description: 角色枚举
 */
public enum RoleEnum {
    MANAGER("manager", "产品经理"), DEV("dev", "开发"), TEST("test", "测试"), PRO("pro", "运维"), SUPERADMIN("superadmin",
            "超级管理员");

    @Getter
    private String eDesc;
    @Getter
    private String cDesc;

    RoleEnum(String eDesc, String cDesc) {
        this.eDesc = eDesc;
        this.cDesc = cDesc;
    }

    /**
     * 根据枚举Code获取枚举类型
     */
    public static RoleEnum getByCode(String eDesc) {
        RoleEnum[] enums = RoleEnum.values();
        for (RoleEnum roleEnum : enums) {
            if (roleEnum.eDesc.equals(eDesc)) {
                return roleEnum;
            }
        }
        return null;
    }

    public static RoleEnum getBycDesc(String cDesc) {
        RoleEnum[] enums = RoleEnum.values();
        for (RoleEnum roleEnum : enums) {
            if (roleEnum.cDesc.equals(cDesc)) {
                return roleEnum;
            }
        }
        return null;
    }
}
