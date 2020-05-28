package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.fei.softwaredevlopmentliftcycle.data.Comment;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.mapper.CommentMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserMapper;
import com.fei.softwaredevlopmentliftcycle.model.comment.CommentModel;
import com.fei.softwaredevlopmentliftcycle.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/13
 * @Description: $value$
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer create(String token, Integer pid, String context) {
        Comment comment = new Comment();
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.USER_PHONE, token).andEqualTo(User.DELETE_TIME,
                "");
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            comment.setUserId(users.get(0).getId());
        }
        comment.setCreateTime(new Date());
        comment.setCommentContext(context);
        comment.setProjectId(pid);
        commentMapper.insertSelective(comment);
        return comment.getId();
    }

    @Override
    public List<CommentModel> getAll(Integer pid) {
        Example example = new Example(Comment.class);
        example.orderBy(Comment.CREATE_TIME).desc();
        example.createCriteria().andEqualTo(Comment.PROJECT_ID, pid).andEqualTo(Comment.DELETE_TIME,
                "");
        example.orderBy(Comment.CREATE_TIME).desc();
        List<Comment> comments = commentMapper.selectByExample(example);
        List<CommentModel> commentModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(comments)) {
            comments.forEach(c -> {
                CommentModel commentModel = new CommentModel();
                BeanUtils.copyProperties(c, commentModel, CommentModel.class);
                Example example1 = new Example(User.class);
                example1.createCriteria().andEqualTo(User.USER_ID, c.getUserId())
                        .andEqualTo(User.DELETE_TIME, "");
                List<User> users = userMapper.selectByExample(example1);
                commentModel.setUsername(users.get(0).getUsername());
                commentModel.setHeadPhoto(users.get(0).getHeadPhoto());
                commentModels.add(commentModel);
            });
        }
        return commentModels;
    }
}
