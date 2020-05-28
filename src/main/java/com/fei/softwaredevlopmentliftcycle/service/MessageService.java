package com.fei.softwaredevlopmentliftcycle.service;

import com.fei.softwaredevlopmentliftcycle.model.message.MessageModel;
import com.fei.softwaredevlopmentliftcycle.model.message.NoticeMessageModel;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
public interface MessageService {
    List<MessageModel> getList(String name);

    void notice(NoticeMessageModel messageModel);

    void delete(Integer mid);

    void read(Integer mid);

    void allRead(String name);

    void noticeByName(String name, String messageTitle);

    Integer notReadNum(String name);

    void noticeByPro(Integer pid, String messageTitle);
}
