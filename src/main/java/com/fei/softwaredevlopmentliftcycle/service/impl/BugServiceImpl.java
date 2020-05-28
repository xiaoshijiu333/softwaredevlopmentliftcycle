package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.fei.softwaredevlopmentliftcycle.data.Bug;
import com.fei.softwaredevlopmentliftcycle.data.Project;
import com.fei.softwaredevlopmentliftcycle.mapper.BugMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.ProjectMapper;
import com.fei.softwaredevlopmentliftcycle.model.bug.BugModel;
import com.fei.softwaredevlopmentliftcycle.model.bug.WebBugModel;
import com.fei.softwaredevlopmentliftcycle.service.BugService;
import com.fei.softwaredevlopmentliftcycle.util.BugStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugMapper bugMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Integer create(WebBugModel webBugModel) {
        Bug bug = new Bug();
        BeanUtils.copyProperties(webBugModel, bug, Bug.class);
        bug.setBugStatus(0);
        bug.setCreateTime(new Date());
        bugMapper.insertSelective(bug);
        return bug.getId();
    }

    @Override
    public List<BugModel> getList(String name, Integer roleNum) {
        Example example = new Example(Bug.class);
        example.orderBy(Bug.CREATE_TIME).desc();
        if (roleNum == 0) {
            example.createCriteria().andEqualTo(Bug.DELETE_TIME, "").andEqualTo(Bug.DEV_NAME, name);
        } else {
            example.createCriteria().andEqualTo(Bug.DELETE_TIME, "").andEqualTo(Bug.TEST_NAME,
                    name);
        }
        List<Bug> bugs = bugMapper.selectByExample(example);
        List<BugModel> bugModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bugs)) {
            bugs.forEach(b -> {
                BugModel bugModel = new BugModel();
                BeanUtils.copyProperties(b, bugModel, BugModel.class);
                Example example1 = new Example(Project.class);
                example1.createCriteria().andEqualTo(Project.PROJECT_ID, b.getProjectId())
                        .andEqualTo(Project.DELETE_TIME, "");
                List<Project> projects = projectMapper.selectByExample(example1);
                bugModel.setProjectName(projects.get(0).getProjectName());
                bugModel.setBugStatus(BugStatusEnum.getByCode(b.getBugStatus()).getStateDesc());
                bugModels.add(bugModel);
            });
            return bugModels;
        }
        return null;
    }

    @Override
    public void edit(WebBugModel bugModel) {
        Bug bug = new Bug();
        BeanUtils.copyProperties(bugModel, bug, Bug.class);
        if (!StringUtils.isEmpty(bugModel.getBugStatus())) {
            bug.setBugStatus(BugStatusEnum.getByDesc(bugModel.getBugStatus()).getStateCode());
        }
        bugMapper.updateByPrimaryKeySelective(bug);
    }
}
