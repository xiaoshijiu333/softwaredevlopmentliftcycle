package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.fei.softwaredevlopmentliftcycle.data.Deploy;
import com.fei.softwaredevlopmentliftcycle.data.Project;
import com.fei.softwaredevlopmentliftcycle.mapper.DeployMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.ProjectMapper;
import com.fei.softwaredevlopmentliftcycle.model.deploy.DeployModel;
import com.fei.softwaredevlopmentliftcycle.service.DeployService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/19
 * @Description: $value$
 */
@Service
public class DeployServiceImpl implements DeployService {

    @Autowired
    private DeployMapper deployMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public String create(DeployModel deployModel) {
        Deploy deploy = new Deploy();
        BeanUtils.copyProperties(deployModel, deploy, Deploy.class);
        deploy.setCreateTime(new Date());
        deployMapper.insertSelective(deploy);
        Project project = projectMapper.selectByPrimaryKey(deployModel.getProjectId());
        return project.getProjectName();
    }

    @Override
    public DeployModel getById(Integer pid) {
        Example example = new Example(Deploy.class);
        example.createCriteria().andEqualTo(Deploy.PROJECT_ID, pid).andEqualTo(Deploy.DELETE_TIME,
                "");
        List<Deploy> deploys = deployMapper.selectByExample(example);
        DeployModel deployModel = new DeployModel();
        BeanUtils.copyProperties(deploys.get(0), deployModel, DeployModel.class);
        Project project = projectMapper.selectByPrimaryKey(deployModel.getProjectId());
        deployModel.setProjectName(project.getProjectName());
        return deployModel;
    }
}
