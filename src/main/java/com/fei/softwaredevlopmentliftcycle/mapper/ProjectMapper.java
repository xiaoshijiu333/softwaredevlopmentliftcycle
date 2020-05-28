package com.fei.softwaredevlopmentliftcycle.mapper;

import com.fei.softwaredevlopmentliftcycle.data.Project;
import com.fei.softwaredevlopmentliftcycle.model.file.WebFileModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: $value$
 */
public interface ProjectMapper extends Mapper<Project> {

    void insertProjectFile(@Param("projectId") Integer projectId,
            @Param("fileModels") List<WebFileModel> fileModels);

}
