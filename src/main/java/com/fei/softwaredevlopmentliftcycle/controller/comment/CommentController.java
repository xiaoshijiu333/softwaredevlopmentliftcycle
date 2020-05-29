package com.fei.softwaredevlopmentliftcycle.controller.comment;

import com.fei.common.server.model.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.comment.CommentModel;
import com.fei.softwaredevlopmentliftcycle.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/13
 * @Description: $value$
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     */
    @PostMapping("/create")
    public ApiResult<Integer> createComment(String token, Integer pid, String context) {
        if (StringUtils.isEmpty(token) || pid == null || StringUtils.isEmpty(context)) {
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(commentService.create(token, pid, context));
    }

    /**
     * 查询该项目所有评论
     */
    @GetMapping("/all")
    public ApiResult<List<CommentModel>> getAll(Integer pid){
        if (pid==null){
            return ApiResult.fail(500, "请求失败，参数错误");
        }
        return ApiResult.ok(commentService.getAll(pid));
    }

}
