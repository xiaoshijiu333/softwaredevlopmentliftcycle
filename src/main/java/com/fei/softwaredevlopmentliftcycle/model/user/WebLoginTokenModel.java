package com.fei.softwaredevlopmentliftcycle.model.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/18
 * @Description: 登录接口前端返回Token模型
 */
public class WebLoginTokenModel implements Serializable {

    private static final long serialVersionUID = 8310603317421158358L;

    @Getter
    @Setter
    private String token;

    public WebLoginTokenModel(String token) {
        this.token = token;
    }

    public WebLoginTokenModel() {

    }
}
