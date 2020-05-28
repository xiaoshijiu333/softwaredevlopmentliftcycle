package com.fei.softwaredevlopmentliftcycle.mapper;

import com.fei.softwaredevlopmentliftcycle.data.Message;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/18
 * @Description: $value$
 */
public interface MessageMapper extends Mapper<Message> {

    void notice(@Param("messageTitle") String messageTitle, @Param("ids") List<Integer> ids);

}
