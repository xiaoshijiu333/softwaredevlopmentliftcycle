package com.fei.softwaredevlopmentliftcycle.service.impl;

import com.fei.common.date.DateUtil;
import com.fei.softwaredevlopmentliftcycle.data.Message;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.data.UserProject;
import com.fei.softwaredevlopmentliftcycle.mapper.MessageMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserMapper;
import com.fei.softwaredevlopmentliftcycle.mapper.UserProjectMapper;
import com.fei.softwaredevlopmentliftcycle.model.message.MessageModel;
import com.fei.softwaredevlopmentliftcycle.model.message.NoticeMessageModel;
import com.fei.softwaredevlopmentliftcycle.service.MessageService;
import com.fei.softwaredevlopmentliftcycle.util.MessageEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Override
    public List<MessageModel> getList(String name) {
        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo(User.USER_NAME, name).andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example1);
        Example example = new Example(Message.class);
        example.orderBy(Message.CREATE_TIME).desc();
        example.createCriteria().andEqualTo(Message.RECEIVE_ID, users.get(0).getId())
                .andEqualTo(Message.DELETE_TIME, "");
        List<Message> messages = messageMapper.selectByExample(example);
        List<MessageModel> messageModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messages)) {
            messages.forEach(m -> {
                MessageModel messageModel = new MessageModel();
                BeanUtils.copyProperties(m, messageModel, MessageModel.class);
                messageModel
                        .setMeaasgeStatus(MessageEnum.getByCode(m.getMessageStatus()).getMsgDesc());
                messageModels.add(messageModel);
            });
            return messageModels;
        }
        return null;
    }

    @Override
    public void notice(NoticeMessageModel messageModel) {
        messageMapper.notice(messageModel.getMessageTitle(), messageModel.getIds());
    }

    @Override
    public void delete(Integer mid) {
        Message message = new Message();
        message.setId(mid);
        message.setDeleteTime(DateUtil.getDeleteTimeStr());
        messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public void read(Integer mid) {
        Message message = new Message();
        message.setId(mid);
        message.setMessageStatus(1);
        messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public void allRead(String name) {
        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo(User.USER_NAME, name).andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example1);
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo(Message.RECEIVE_ID, users.get(0).getId())
                .andEqualTo(Message.DELETE_TIME, "").andEqualTo(Message.MESSAGE_STATUE, 0);
        Message message = new Message();
        message.setMessageStatus(1);
        messageMapper.updateByExampleSelective(message, example);
    }

    @Override
    public void noticeByName(String name, String messageTitle) {
        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo(User.USER_NAME, name).andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example1);
        Message message = new Message();
        message.setMessageTitle(messageTitle);
        message.setReceiveId(users.get(0).getId());
        message.setMessageStatus(0);
        message.setCreateTime(new Date());
        messageMapper.insertSelective(message);
    }

    @Override
    public Integer notReadNum(String name) {
        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo(User.USER_NAME, name).andEqualTo(User.DELETE_TIME, "");
        List<User> users = userMapper.selectByExample(example1);
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo(Message.DELETE_TIME, "")
                .andEqualTo(Message.RECEIVE_ID, users.get(0).getId())
                .andEqualTo(Message.MESSAGE_STATUE, 0);
        return messageMapper.selectCountByExample(example);
    }

    @Override
    public void noticeByPro(Integer pid, String messageTitle) {
        Example example = new Example(UserProject.class);
        example.createCriteria().andEqualTo(UserProject.PROJECT_ID, pid)
                .andEqualTo(UserProject.DELETE_TIME, "");
        List<UserProject> userProjects = userProjectMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userProjects)) {
            List<Integer> list = userProjects.stream().map(UserProject::getUserId)
                    .collect(Collectors.toList());
            messageMapper.notice(messageTitle, list);
        }
    }
}
