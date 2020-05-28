package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.cases.CaseModel;
import com.fei.softwaredevlopmentliftcycle.model.cases.WebCaseModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
public interface CaseServeice {
    Integer create(WebCaseModel webCaseModel);

    List<CaseModel> getList(String name, Integer pid);

    void delete(Integer cid);

    void edit(WebCaseModel webCaseModel);

    CaseModel getById(Integer cid);
}
