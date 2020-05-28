package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.comment.CommentModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/13
 * @Description: $value$
 */
public interface CommentService {
    Integer create(String token, Integer pid, String context);

    List<CommentModel> getAll(Integer pid);
}
