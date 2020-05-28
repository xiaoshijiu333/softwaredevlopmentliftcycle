package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.data.Case;
import com.fei.softwaredevlopmentliftcycle.mapper.CaseMapper;
import com.fei.softwaredevlopmentliftcycle.model.cases.CaseModel;
import com.fei.softwaredevlopmentliftcycle.model.cases.WebCaseModel;
import com.fei.softwaredevlopmentliftcycle.service.CaseServeice;
import com.fei.softwaredevlopmentliftcycle.util.CaseStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@Service
public class CaseServeiceImpl implements CaseServeice {

    @Autowired
    private CaseMapper caseMapper;

    @Override
    public Integer create(WebCaseModel webCaseModel) {
        Case aCase = new Case();
        BeanUtils.copyProperties(webCaseModel, aCase, Case.class);
        aCase.setCaseStatus(0);
        caseMapper.insertSelective(aCase);
        return aCase.getId();
    }

    @Override
    public List<CaseModel> getList(String name, Integer pid) {
        Example example = new Example(Case.class);
        example.orderBy(Case.CREATE_TIME).desc();
        if (StringUtils.isEmpty(name)) {
            example.createCriteria().andEqualTo(Case.PROJECT_ID, pid).andEqualTo(Case.DELETE_TIME,
                    "");
        } else {
            example.createCriteria().andEqualTo(Case.PROJECT_ID, pid)
                    .andEqualTo(Case.DELETE_TIME, "").andEqualTo(Case.TEST_NAME, name);
        }
        List<Case> cases = caseMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cases)) {
            List<CaseModel> caseModels = new ArrayList<>();
            cases.forEach(c -> {
                CaseModel caseModel = new CaseModel();
                BeanUtils.copyProperties(c, caseModel, CaseModel.class);
                caseModel.setCaseStatus(CaseStatusEnum.getByCode(c.getCaseStatus()).getStateDesc());
                caseModels.add(caseModel);
            });
            return caseModels;
        }
        return null;
    }

    @Override
    public void delete(Integer cid) {
        Case aCase = new Case();
        aCase.setId(cid);
        aCase.setDeleteTime(DateUtil.getDeleteTimeStr());
        caseMapper.updateByPrimaryKeySelective(aCase);
    }

    @Override
    public void edit(WebCaseModel webCaseModel) {
        Case aCase = new Case();
        BeanUtils.copyProperties(webCaseModel, aCase, Case.class);
        if (webCaseModel.getCaseStatus() != null) {
            aCase.setCaseStatus(
                    CaseStatusEnum.getByDesc(webCaseModel.getCaseStatus()).getStateCode());
        }
        caseMapper.updateByPrimaryKeySelective(aCase);
    }

    @Override
    public CaseModel getById(Integer cid) {
        Example example = new Example(Case.class);
        example.createCriteria().andEqualTo(Case.CASE_ID, cid).andEqualTo(Case.DELETE_TIME, "");
        List<Case> cases = caseMapper.selectByExample(example);
        CaseModel caseModel = new CaseModel();
        BeanUtils.copyProperties(cases.get(0), caseModel, CaseModel.class);
        caseModel.setCaseStatus(
                CaseStatusEnum.getByCode(cases.get(0).getCaseStatus()).getStateDesc());
        return caseModel;
    }
}
