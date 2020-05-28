package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.task.TaskModel;
import com.fei.softwaredevlopmentliftcycle.model.task.WebTaskModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
public interface TaskService {
    Integer create(WebTaskModel taskModel);

    List<TaskModel> getList(String name, Integer pid);

    void delete(Integer tid);

    void edit(WebTaskModel taskModel);

    TaskModel getById(Integer tid);

}
