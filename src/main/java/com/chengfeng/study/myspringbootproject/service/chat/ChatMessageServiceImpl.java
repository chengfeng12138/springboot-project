package com.chengfeng.study.myspringbootproject.service.chat;

import cn.hutool.core.date.DateUtil;
import com.chengfeng.study.myspringbootproject.mapper.chat.ChatMessageMapper;
import com.chengfeng.study.myspringbootproject.pojo.ChatMessage;
import com.chengfeng.study.myspringbootproject.websocket.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ChatMessageServiceImpl class
 *
 * @author chengfeng
 * @date 2021/9/5 /0005 1:27
 */
@Service("chatMessageServiceImpl")
public class ChatMessageServiceImpl implements ChatMessageService{

    private static final Logger log = Logger.getLogger(ChatMessageServiceImpl.class.getName());

    @Autowired
    ChatMessageMapper chatMessageMapper;
    @Autowired
    WebSocketClient webSocketClient;

    /**
     * 发送聊天消息
     *
     * @author chengfeng
     * @date 2021/8/30 /0030 22:11
     */
    @Override
    public Integer sendChatMessage(String from, String to, Object message) {
        try {
            //发送消息
            log.log(Level.INFO, "发送聊天消息~ from: " + from + " ; to: " + to + " ; message: " + message);
            boolean sendResult = webSocketClient.chatSendMessage(from, to, message.toString());
            if (!sendResult) {
                return null;
            }
            //保存消息到数据库
            ChatMessage chatMessage = new ChatMessage(from, to, message.toString(), DateUtil.now());
            return chatMessageMapper.saveChatMessage(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询和好友的聊天信息
     * @author chengfeng
     * @date 2021/8/31 /0031 20:42
     */
    @Override
    public List<ChatMessage> findChatMessageByOne(String from, String to) {
        return chatMessageMapper.findChatMessageByOne(from, to);
    }

}
