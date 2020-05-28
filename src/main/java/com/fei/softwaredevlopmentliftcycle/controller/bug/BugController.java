package com.fei.softwaredevlopmentliftcycle.controller.bug;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.bug.BugModel;
import com.fei.softwaredevlopmentliftcycle.model.bug.WebBugModel;
import com.fei.softwaredevlopmentliftcycle.model.cases.CaseModel;
import com.fei.softwaredevlopmentliftcycle.service.BugService;
import com.fei.softwaredevlopmentliftcycle.util.DownloadExcel;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2020/5/16
 * @Description: $value$
 */
@RestController
@RequestMapping("/bug")
public class BugController {

    @Autowired
    private BugService bugService;

    @PostMapping("/create")
    public ApiResult<Integer> create(@RequestBody WebBugModel webBugModel) {
        if (StringUtils.isEmpty(webBugModel.getBugName())) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(bugService.create(webBugModel));
    }

    @GetMapping("/getList")
    public ApiResult<List<BugModel>> getList(String name, Integer roleNum) {
        if (StringUtils.isEmpty(name) || roleNum == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(bugService.getList(name, roleNum));
    }

    @GetMapping("/excel")
    public ResponseEntity downExcel(String name, Integer roleNum)
            throws UnsupportedEncodingException {
        List<BugModel> bugModels = bugService.getList(name, roleNum);
        String[] titles = { "缺陷名称", "缺陷描述", "所属项目", "开始时间", "测试负责人姓名", "开发负责人姓名", "状态" };

        String[][] datas = new String[bugModels.size()][titles.length];
        for (int i = 0, size = bugModels.size(); i < size; i++) {
            // 赋值
            datas[i][0] = String.valueOf(bugModels.get(i).getBugName());
            datas[i][1] = bugModels.get(i).getBugDesc();
            datas[i][2] = bugModels.get(i).getProjectName();
            datas[i][3] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(bugModels.get(i).getCreateTime());
            datas[i][4] = bugModels.get(i).getTestName();
            datas[i][5] = bugModels.get(i).getDevName();
            datas[i][6] = bugModels.get(i).getBugStatus();
        }

        String filename = "缺陷信息列表";
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

    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody WebBugModel bugModel) {
        if (bugModel.getId() == null) {
            return ApiResult.fail(500, "参数为空，请求失败");
        }
        bugService.edit(bugModel);
        return ApiResult.ok("修改成功");
    }
}
