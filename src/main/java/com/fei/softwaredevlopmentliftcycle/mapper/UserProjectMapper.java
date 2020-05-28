package com.fei.softwaredevlopmentliftcycle.mapper;

import com.fei.softwaredevlopmentliftcycle.data.UserProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/21
 * @Description: $value$
 */
public interface UserProjectMapper extends Mapper<UserProject> {

    void create(@Param("projectId") Integer projectId,
            @Param("projectPeopleIds") List<Integer> projectPeopleIds);

}
