package com.fei.softwaredevlopmentliftcycle.controller.project;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.project.ProjectModel;
import com.fei.softwaredevlopmentliftcycle.model.project.WebCreateProjectModel;
import com.fei.softwaredevlopmentliftcycle.service.ProjectService;
import com.fei.softwaredevlopmentliftcycle.util.StateEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: 项目相关控制器
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 根据用户token获取用户关联的项目，isDoing筛选正在进行的
     */
    @GetMapping("/getList")
    public ApiResult<List<ProjectModel>> getListByToken(String token, Boolean isDoing,
            Integer status) {
        if (StringUtils.isEmpty(token)) {
            return ApiResult.fail(500, "请求失败,token不存在");
        }
        return ApiResult.ok(projectService.getListByToken(token, isDoing, status));
    }

    /**
     * 新建一个项目
     */
    @PostMapping("/create")
    public ApiResult<Integer> create(@RequestBody WebCreateProjectModel webCreateProjectModel) {
        if (webCreateProjectModel == null || webCreateProjectModel.getProjectName() == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(projectService.create(webCreateProjectModel));
    }

    /**
     * 终止项目
     */
    @PostMapping("/delete")
    public ApiResult<String> delete(Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，ID为空");
        }
        projectService.delete(pid);
        return ApiResult.ok("删除成功");
    }

    /**
     * 根据id获取项目详情
     */
    @GetMapping("/getById")
    public ApiResult<ProjectModel> getById(Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，ID为空");
        }
        return ApiResult.ok(projectService.getById(pid));
    }

    /**
     * 编辑项目
     */
    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody WebCreateProjectModel webCreateProjectModel) {
        if (webCreateProjectModel == null || webCreateProjectModel.getProjectName() == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        projectService.edit(webCreateProjectModel);
        return ApiResult.ok("更新成功");
    }

    /**
     * 项目状态更迭
     */
    @PostMapping("/editState")
    public ApiResult<String> editState(Integer pid, Integer projectState) {
        if (projectState == null || pid == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        projectService.editState(pid, projectState);
        String stateDesc = StateEnum.getByCode(projectState + 1).getStateDesc();
        return ApiResult.ok(stateDesc);
    }
}
