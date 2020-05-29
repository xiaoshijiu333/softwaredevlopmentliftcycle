package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.alibaba.fastjson.JSON;
import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.data.Task;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.mapper.TaskMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserMapper;
import com.fei.softwaredevlopmentliftcycle.model.task.TaskModel;
import com.fei.softwaredevlopmentliftcycle.model.task.WebTaskModel;
import com.fei.softwaredevlopmentliftcycle.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
@Service
public class TaskServeiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer create(WebTaskModel taskModel) {
        Task task = new Task();
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.USER_PHONE, taskModel.getToken())
                .andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example);
        BeanUtils.copyProperties(taskModel, task, Task.class);
        task.setDevId(users.get(0).getId());
        task.setDevName(users.get(0).getUsername());
        taskMapper.insertSelective(task);
        return task.getId();
    }

    @Override
    public List<TaskModel> getList(String name, Integer pid) {
        Example example1 = new Example(Task.class);
        if (StringUtils.isEmpty(name)) {
            example1.createCriteria().andEqualTo(Task.PROJECT_ID, pid)
                    .andEqualTo(Task.DELETE_TIME, "").andIsNull(Task.END_TIME);
        } else {
            example1.createCriteria().andEqualTo(Task.PROJECT_ID, pid)
                    .andEqualTo(Task.DEV_NAME, name).andEqualTo(Task.DELETE_TIME, "")
                    .andIsNull(Task.END_TIME);
        }
        example1.orderBy(Task.CREATE_TIME).desc();
        List<Task> tasks = taskMapper.selectByExample(example1);
        return JSON.parseArray(JSON.toJSONString(tasks), TaskModel.class);
    }

    @Override
    public void delete(Integer tid) {
        Task task = new Task();
        task.setId(tid);
        task.setDeleteTime(DateUtil.getDeleteTimeStr());
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public void edit(WebTaskModel taskModel) {
        Task task = new Task();
        BeanUtils.copyProperties(taskModel, task, Task.class);
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public TaskModel getById(Integer tid) {
        Example example = new Example(Task.class);
        example.createCriteria().andEqualTo(Task.TASK_ID, tid).andEqualTo(Task.DELETE_TIME, "");
        List<Task> tasks = taskMapper.selectByExample(example);
        TaskModel taskModel = new TaskModel();
        BeanUtils.copyProperties(tasks.get(0), taskModel, TaskModel.class);
        return taskModel;
    }
}
