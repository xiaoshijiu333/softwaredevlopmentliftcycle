package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.alibaba.fastjson.JSON;
import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.data.Project;
import com.fei.softwaredevlopmentliftcycle.data.ProjectFile;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.data.UserProject;
import com.fei.softwaredevlopmentliftcycle.data.UserRole;
import com.fei.softwaredevlopmentliftcycle.mapper.ProjectFileMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.ProjectMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserProjectMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserRoleMapper;
import com.fei.softwaredevlopmentliftcycle.model.file.WebFileModel;
import com.fei.softwaredevlopmentliftcycle.model.project.ProjectModel;
import com.fei.softwaredevlopmentliftcycle.model.project.WebCreateProjectModel;
import com.fei.softwaredevlopmentliftcycle.model.user.UserModel;
import com.fei.softwaredevlopmentliftcycle.service.ProjectService;
import com.fei.softwaredevlopmentliftcycle.util.RoleEnum;
import com.fei.softwaredevlopmentliftcycle.util.StateEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: $value$
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectFileMapper projectFileMapper;

    @Override
    public List<ProjectModel> getListByToken(String token, Boolean isDoing, Integer status) {
        // token得到用户id
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.USER_PHONE, token).andEqualTo(User.DELETE_TIME,
                "");
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            Example example1 = new Example(UserProject.class);
            example1.createCriteria().andEqualTo(UserProject.USER_ID, users.get(0).getId())
                    .andEqualTo(UserProject.DELETE_TIME, "");
            List<UserProject> userProjects = userProjectMapper.selectByExample(example1);
            if (!CollectionUtils.isEmpty(userProjects)) {
                Example example2 = new Example(Project.class);
                if (status != null) {
                    example2.createCriteria()
                            .andIn(Project.PROJECT_ID,
                                    userProjects.stream().map(UserProject::getProjectId)
                                            .collect(Collectors.toList()))
                            .andEqualTo(Project.DELETE_TIME, "")
                            .andEqualTo(Project.PROJECT_STATE, status);
                } else {
                    if (isDoing) {
                        example2.createCriteria()
                                .andIn(Project.PROJECT_ID,
                                        userProjects.stream().map(UserProject::getProjectId)
                                                .collect(Collectors.toList()))
                                .andNotEqualTo(Project.PROJECT_STATE, 4)
                                .andEqualTo(Project.DELETE_TIME, "");
                    } else {
                        example2.createCriteria()
                                .andIn(Project.PROJECT_ID,
                                        userProjects.stream().map(UserProject::getProjectId)
                                                .collect(Collectors.toList()))
                                .andEqualTo(Project.DELETE_TIME, "");
                    }
                }

                // 按照创建时间倒叙
                example2.orderBy(Project.CREATE_TIME).desc();
                List<Project> projects = projectMapper.selectByExample(example2);
                if (!CollectionUtils.isEmpty(projects)) {
                    List<ProjectModel> projectModels = new ArrayList<>();
                    List<ProjectModel> projectModelsFinashes = new ArrayList<>();
                    projects.forEach(p -> {
                        ProjectModel projectModel = new ProjectModel();
                        // 单独接受已完成的，因为需要将已完成的放到末尾去
                        BeanUtils.copyProperties(p, projectModel, ProjectModel.class);
                        // 处理状态
                        projectModel.setProjectStateDesc(
                                StateEnum.getByCode(p.getProjectState()).getStateDesc());
                        if (p.getProjectState() == 4) {
                            projectModelsFinashes.add(projectModel);
                        } else {
                            projectModels.add(projectModel);
                        }
                    });
                    projectModels.addAll(projectModelsFinashes);
                    return projectModels;
                }
            }
        }
        return null;
    }

    @Override
    public int create(WebCreateProjectModel webCreateProjectModel) {
        Project project = new Project();
        project.setProjectState(0);
        BeanUtils.copyProperties(webCreateProjectModel, project, Project.class);
        // 注意：返回的是操作的行数，主键id包含在对象本身
        // insertSelective有选择的插入，即自动过滤掉对象为空的属性
        projectMapper.insertSelective(project);
        // 处理人员;;;注意事项：通用mapper只适合单表操作，设计多表以及复杂批量sql还是推荐使用mybatis自己写sql的方式
        userProjectMapper.create(project.getId(), webCreateProjectModel.getProjectPeopleIds());
        // 处理文件（同理上面）
        projectMapper.insertProjectFile(project.getId(),
                webCreateProjectModel.getProjectFileUrls());
        return project.getId();
    }

    @Override
    public void delete(Integer pid) {
        Project project = new Project();
        project.setId(pid);
        // 设置更新删除时间，即删除
        project.setDeleteTime(DateUtil.getDeleteTimeStr());
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public ProjectModel getById(Integer pid) {
        Example example = new Example(Project.class);
        example.createCriteria().andEqualTo(Project.PROJECT_ID, pid).andEqualTo(Project.DELETE_TIME,
                "");
        List<Project> projects = projectMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(projects)) {
            ProjectModel projectModel = new ProjectModel();
            Project project = projects.get(0);
            BeanUtils.copyProperties(project, projectModel, ProjectModel.class);
            // 处理状态
            projectModel.setProjectStateDesc(
                    StateEnum.getByCode(project.getProjectState()).getStateDesc());
            // 查文件
            Example example1 = new Example(ProjectFile.class);
            example1.createCriteria().andEqualTo(ProjectFile.PROJECT_ID, project.getId())
                    .andEqualTo(ProjectFile.DELETE_TIME, "");
            List<ProjectFile> projectFiles = projectFileMapper.selectByExample(example1);
            // FastJson处理集合模型转变
            List<WebFileModel> webFileModels = JSON.parseArray(JSON.toJSONString(projectFiles),
                    WebFileModel.class);
            projectModel.setProjectFileUrls(webFileModels);
            // 关联人员处理
            Example example2 = new Example(UserProject.class);
            example2.createCriteria().andEqualTo(UserProject.PROJECT_ID, project.getId())
                    .andEqualTo(UserProject.DELETE_TIME, "");
            List<UserProject> userProjects = userProjectMapper.selectByExample(example2);
            List<Integer> list = userProjects.stream().map(UserProject::getUserId)
                    .collect(Collectors.toList());
            projectModel.setProjectPeopleIds(list);
            // 人员姓名
            Example example3 = new Example(User.class);
            example3.createCriteria().andEqualTo(User.DELETE_TIME, "").andIn(User.USER_ID, list);
            List<User> users = userMapper.selectByExample(example3);
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < users.size(); i++) {
                if (i == 0) {
                    names.append(users.get(i).getUsername());
                } else {
                    names.append("，" + users.get(i).getUsername());
                }
            }
            projectModel.setProjectPeople(names.toString());

            Example example4 = new Example(User.class);
            example4.createCriteria().andEqualTo(User.DELETE_TIME, "").andIn(User.USER_ID, list);
            List<User> users1 = userMapper.selectByExample(example4);
            if (!CollectionUtils.isEmpty(users1)) {
                List<UserModel> userModels = new ArrayList<>();
                users1.forEach(u -> {
                    UserModel userModel = new UserModel();
                    BeanUtils.copyProperties(u, userModel, UserModel.class);
                    Example example5 = new Example(UserRole.class);
                    example5.createCriteria().andEqualTo(UserRole.USER_ID, u.getId())
                            .andEqualTo(UserRole.DELETE_TIME, "");
                    List<UserRole> userRoles = userRoleMapper.selectByExample(example5);
                    List<String> list1 = userRoles.stream().map(UserRole::getRoleName)
                            .collect(Collectors.toList());
                    // 角色处理
                    userModel.setUserRoles(list1.stream().map(s -> RoleEnum.getByCode(s).getCDesc())
                            .collect(Collectors.toList()));
                    userModels.add(userModel);
                });
                projectModel.setProjectPeoples(userModels);
            }
            return projectModel;
        }
        return null;
    }

    @Override
    public void edit(WebCreateProjectModel webCreateProjectModel) {
        Project project = new Project();
        BeanUtils.copyProperties(webCreateProjectModel, project, Project.class);
        projectMapper.updateByPrimaryKeySelective(project);
        // 人员信息更新，先删除在添加
        Example example = new Example(UserProject.class);
        UserProject userProject = new UserProject();
        userProject.setDeleteTime(DateUtil.getDeleteTimeStr());
        example.createCriteria().andEqualTo(UserProject.PROJECT_ID, webCreateProjectModel.getId())
                .andEqualTo(UserProject.DELETE_TIME, "");
        userProjectMapper.updateByExampleSelective(userProject, example);
        userProjectMapper.create(webCreateProjectModel.getId(),
                webCreateProjectModel.getProjectPeopleIds());
        // 文件信息更新
        ProjectFile projectFile = new ProjectFile();
        projectFile.setDeleteTime(DateUtil.getDeleteTimeStr());
        Example example1 = new Example(ProjectFile.class);
        example1.createCriteria().andEqualTo(ProjectFile.PROJECT_ID, webCreateProjectModel.getId())
                .andEqualTo(ProjectFile.DELETE_TIME, "");
        projectFileMapper.updateByExampleSelective(projectFile, example1);
        projectMapper.insertProjectFile(webCreateProjectModel.getId(),
                webCreateProjectModel.getProjectFileUrls());
    }

    @Override
    public void editState(Integer pid, Integer projectState) {
        Project project = new Project();
        project.setId(pid);
        project.setProjectState(projectState + 1);
        projectMapper.updateByPrimaryKeySelective(project);
    }
}
