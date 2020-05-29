package com.fei.softwaredevlopmentliftcycle.controller.message;

import com.fei.common.constant.StatusConstant;
import com.fei.common.server.model.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.message.MessageModel;
import com.fei.softwaredevlopmentliftcycle.model.message.NoticeMessageModel;
import com.fei.softwaredevlopmentliftcycle.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getList")
    public ApiResult<List<MessageModel>> getList(String name) {
        if (StringUtils.isEmpty(name)) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        return ApiResult.ok(messageService.getList(name));
    }

    @PostMapping("/notice")
    public ApiResult<String> notice(@RequestBody NoticeMessageModel messageModel) {
        if (StringUtils.isEmpty(messageModel.getMessageTitle())
                || CollectionUtils.isEmpty(messageModel.getIds())) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.notice(messageModel);
        return ApiResult.ok("通知成功");
    }

    @PostMapping("/noticeByPro")
    public ApiResult<String> noticeByPro(Integer pid, String messageTitle) {
        if (pid == null || StringUtils.isEmpty(messageTitle)) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.noticeByPro(pid, messageTitle);
        return ApiResult.ok("通知成功");
    }

    @PostMapping("/noticeByName")
    public ApiResult<String> noticeByName(String name, String messageTitle) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(messageTitle)) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.noticeByName(name, messageTitle);
        return ApiResult.ok("通知成功");
    }

    @PostMapping("/delete")
    public ApiResult<String> delete(Integer mid) {
        if (mid == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.delete(mid);
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/read")
    public ApiResult<String> read(Integer mid) {
        if (mid == null) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.read(mid);
        return ApiResult.ok("标为已读成功");
    }

    @PostMapping("/allRead")
    public ApiResult<String> allRead(String name) {
        if (StringUtils.isEmpty(name)) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        messageService.allRead(name);
        return ApiResult.ok("全部标为已读成功");
    }

    @GetMapping("/notReadNum")
    public ApiResult<Integer> notReadNum(String name) {
        if (StringUtils.isEmpty(name)) {
            return ApiResult.fail(500, StatusConstant.FAILTURE_MESSAGE_PARAMETER_ERROR);
        }
        return ApiResult.ok(messageService.notReadNum(name));
    }

}
