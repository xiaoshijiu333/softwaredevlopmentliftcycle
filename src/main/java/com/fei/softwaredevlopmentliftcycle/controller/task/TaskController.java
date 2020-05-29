package com.fei.softwaredevlopmentliftcycle.controller.task;

import com.fei.common.constant.StatusConstant;
import com.fei.common.server.model.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.gante.GanTeModel;
import com.fei.softwaredevlopmentliftcycle.model.gante.ParamsModel;
import com.fei.softwaredevlopmentliftcycle.model.task.TaskModel;
import com.fei.softwaredevlopmentliftcycle.model.task.WebTaskModel;
import com.fei.softwaredevlopmentliftcycle.service.TaskService;
import com.fei.softwaredevlopmentliftcycle.util.DownloadExcel;
import com.fei.softwaredevlopmentliftcycle.util.TimeAddHours;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/14
 * @Description: $value$
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ApiResult<Integer> create(@RequestBody WebTaskModel taskModel) {
        if (taskModel.getTaskName() == null) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(taskService.create(taskModel));
    }

    @GetMapping("/getList")
    public ApiResult<List<TaskModel>> getList(String name, Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        return ApiResult.ok(taskService.getList(name, pid));
    }

    @PostMapping("/delete")
    public ApiResult<String> delete(Integer tid) {
        if (tid == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        taskService.delete(tid);
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/edit")
    public ApiResult<Integer> edit(@RequestBody WebTaskModel webTaskModel) {
        if (webTaskModel.getTid() == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        taskService.edit(webTaskModel);
        return ApiResult.ok(webTaskModel.getProjectId());
    }

    @GetMapping("/getById")
    public ApiResult<TaskModel> getById(Integer tid) {
        if (tid == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        return ApiResult.ok(taskService.getById(tid));
    }

    @GetMapping("/excel")
    public ResponseEntity downExcel(String name, Integer pid) throws UnsupportedEncodingException {
        List<TaskModel> taskModels = taskService.getList(name, pid);
        String[] titles = { "任务名称", "任务描述", "开始时间", "预计耗时", "负责人姓名" };

        String[][] datas = new String[taskModels.size()][titles.length];
        for (int i = 0, size = taskModels.size(); i < size; i++) {
            // 赋值
            datas[i][0] = String.valueOf(taskModels.get(i).getTaskName());
            datas[i][1] = taskModels.get(i).getTaskDesc();
            datas[i][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(taskModels.get(i).getCreateTime());
            datas[i][3] = String.valueOf(taskModels.get(i).getPreCost());
            datas[i][4] = taskModels.get(i).getDevName();
        }

        String filename = "任务信息列表";
        HSSFWorkbook workbook = DownloadExcel.downloadExcel(filename, titles, datas);

        // 创建响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        filename = URLEncoder.encode(filename + ".xls", "UTF-8");
        httpHeaders.add("Content-Disposition", "attachment;filename=" + filename);

        // 将byte数组、HttpHeader和HttpStatus传入ResponseEntity
        return new ResponseEntity<>(workbook.getBytes(), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/gante")
    public ApiResult<List<GanTeModel>> gante(Integer pid) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        List<TaskModel> list = taskService.getList("", pid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<GanTeModel> ganTeModels = new ArrayList<>();
        list.forEach(t -> {
            GanTeModel ganTeModel = new GanTeModel();
            ParamsModel paramsModel = new ParamsModel();
            ganTeModel.setStart_time(t.getCreateTime().getTime());
            // 加上预计的小时数
            Date endTime = TimeAddHours.addDateMinut(t.getCreateTime(), t.getPreCost());
            ganTeModel.setEnd_time(endTime.getTime());
            ganTeModel.setLevel(1);
            ganTeModel.setGunter_id(t.getId());
            paramsModel.setStartTime(format.format(t.getCreateTime()));
            paramsModel.setEndTime(format.format(endTime));
            paramsModel.setTaskName(t.getTaskName());
            paramsModel.setDevName(t.getDevName());
            ganTeModel.setParams(paramsModel);
            ganTeModels.add(ganTeModel);
        });
        return ApiResult.ok(ganTeModels);
    }
}
