package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.deploy.DeployModel;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/19
 * @Description: $value$
 */
public interface DeployService {
    String create(DeployModel deployModel);

    DeployModel getById(Integer pid);
}
