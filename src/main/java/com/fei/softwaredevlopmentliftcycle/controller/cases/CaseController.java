package com.fei.softwaredevlopmentliftcycle.controller.cases;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.cases.CaseModel;
import com.fei.softwaredevlopmentliftcycle.model.cases.WebCaseModel;
import com.fei.softwaredevlopmentliftcycle.service.CaseServeice;
import com.fei.softwaredevlopmentliftcycle.util.DownloadExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/15
 * @Description: $value$
 */
@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseServeice caseServeice;

    @PostMapping("/create")
    public ApiResult<Integer> create(@RequestBody WebCaseModel webCaseModel) {
        if (webCaseModel.getCaseName() == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(caseServeice.create(webCaseModel));
    }

    @GetMapping("/getList")
    public ApiResult<List<CaseModel>> getList(String name, Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(caseServeice.getList(name, pid));
    }

    @PostMapping("/delete")
    public ApiResult<String> delete(Integer cid) {
        if (cid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        caseServeice.delete(cid);
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/edit")
    public ApiResult<Integer> edit(@RequestBody WebCaseModel webCaseModel) {
        if (webCaseModel.getId() == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        caseServeice.edit(webCaseModel);
        return ApiResult.ok();
    }

    @GetMapping("/getById")
    public ApiResult<CaseModel> getById(Integer cid) {
        if (cid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(caseServeice.getById(cid));
    }

    @GetMapping("/excel")
    public ResponseEntity downExcel(String name, Integer pid) throws UnsupportedEncodingException {
        List<CaseModel> caseModels = caseServeice.getList(name, pid);
        String[] titles = { "测试用例名称", "测试用例描述", "开始时间", "预计耗时", "负责人姓名", "对应开发人员姓名", "状态" };

        String[][] datas = new String[caseModels.size()][titles.length];
        for (int i = 0, size = caseModels.size(); i < size; i++) {
            // 赋值
            datas[i][0] = String.valueOf(caseModels.get(i).getCaseName());
            datas[i][1] = caseModels.get(i).getCaseDesc();
            datas[i][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(caseModels.get(i).getCreateTime());
            datas[i][3] = String.valueOf(caseModels.get(i).getPreCost());
            datas[i][4] = caseModels.get(i).getTestName();
            datas[i][5] = caseModels.get(i).getDevName();
            datas[i][6] = caseModels.get(i).getCaseStatus();
        }

        String filename = "测试用例信息列表";
        HSSFWorkbook workbook = DownloadExcel.downloadExcel(filename, titles, datas);

        // 创建响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        filename = URLEncoder.encode(filename + ".xls", "UTF-8");
        httpHeaders.add("Content-Disposition", "attachment;filename=" + filename);

        // 将byte数组、HttpHeader和HttpStatus传入ResponseEntity
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(workbook.getBytes(),
                httpHeaders, HttpStatus.OK);
        return responseEntity;
    }
}
