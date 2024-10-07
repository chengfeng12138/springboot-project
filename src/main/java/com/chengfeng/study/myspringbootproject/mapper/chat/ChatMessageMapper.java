package com.chengfeng.study.myspringbootproject.mapper.chat;

import com.chengfeng.study.myspringbootproject.pojo.ChatMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatMessageMapper class
 *
 * @author chengfeng
 * @date 2021/8/30 /0030 21:56
 */
@Repository
public interface ChatMessageMapper {

    /**
     * 保存聊天信息
     *
     * @param chatMessage 聊天消息对象
     * @return 消息id
     * @author chengfeng
     * @date 2021/8/30 /0030 22:03
     */
    Integer saveChatMessage(ChatMessage chatMessage);

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
