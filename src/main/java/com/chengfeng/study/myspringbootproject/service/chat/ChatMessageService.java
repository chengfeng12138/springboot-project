package com.chengfeng.study.myspringbootproject.service.chat;

import com.chengfeng.study.myspringbootproject.pojo.ChatMessage;

import java.util.List;

/**
 * ChatMessageService class
 * 聊天消息处理接口
 * @author chengfeng
 * @date 2021/9/5 /0005 1:29
 */
public interface ChatMessageService {

    /**
     * 发送聊天消息
     * @param from 发送者
     * @param to 接收者
     * @param message 消息
     * @return 消息id
     * @author chengfeng
     * @date 2021/8/30 /0030 22:03
     */
    Integer sendChatMessage(String from, String to, Object message);

    /**
     * 查询和好友的聊天信息
     * @param from 当前用户
     * @param to 好友
     * @return 消息id
     * @author chengfeng
     * @date 2021/8/30 /0030 22:03
     */
    List<ChatMessage> findChatMessageByOne(String from, String to);

}
