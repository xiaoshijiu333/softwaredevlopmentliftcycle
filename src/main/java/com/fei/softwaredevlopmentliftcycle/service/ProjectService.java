package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.project.ProjectModel;
import com.fei.softwaredevlopmentliftcycle.model.project.WebCreateProjectModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: $value$
 */
public interface ProjectService {

    /**
     * 根据用户token获取用户关联的项目
     * @param isDoing 是否正在进行
     */
    List<ProjectModel> getListByToken(String token, Boolean isDoing,Integer status);

    /**
     * 创建项目
     * @return 新创建的项目id
     */
    int create(WebCreateProjectModel webCreateProjectModel);

    /**
     * 删除项目，没有返回值
     */
    void delete(Integer pid);

    /**
     * 根据id获取项目详情
     */
    ProjectModel getById(Integer pid);

    /**
     * 编辑修改项目
     */
    void edit(WebCreateProjectModel webCreateProjectModel);

    void editState(Integer pid, Integer projectState);
}
