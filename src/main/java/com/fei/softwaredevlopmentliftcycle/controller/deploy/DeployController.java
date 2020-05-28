package com.fei.softwaredevlopmentliftcycle.controller.deploy;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.deploy.DeployModel;
import com.fei.softwaredevlopmentliftcycle.service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/19
 * @Description: $value$
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    @Autowired
    private DeployService deployService;

    @PostMapping("/create")
    public ApiResult<String> create(@RequestBody DeployModel deployModel) {
        if (deployModel.getProjectId() == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(deployService.create(deployModel));
    }

    @GetMapping("/getById")
    public ApiResult<DeployModel> getById(Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(deployService.getById(pid));
    }

}
