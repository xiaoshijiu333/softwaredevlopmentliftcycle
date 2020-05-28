package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.bug.BugModel;
import com.fei.softwaredevlopmentliftcycle.model.bug.WebBugModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
public interface BugService {
    Integer create(WebBugModel webBugModel);

    List<BugModel> getList(String name, Integer roleNum);

    void edit(WebBugModel bugModel);
}
