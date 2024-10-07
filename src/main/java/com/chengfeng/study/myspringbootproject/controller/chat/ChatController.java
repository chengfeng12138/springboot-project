package com.chengfeng.study.myspringbootproject.controller.chat;

import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.ChatMessage;
import com.chengfeng.study.myspringbootproject.service.chat.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatController class
 * 聊天controller
 * @author chengfeng
 * @date 2021/8/30 /0030 21:39
 */
@RestController
public class ChatController {

    @Autowired
    @Qualifier("chatMessageServiceImpl")
    ChatMessageService chatMessageService;

    /**
    * 发送聊天消息
    * @author chengfeng
    * @date 2021/8/30 /0030 22:09
    */
    @RequestMapping("/action/sendChatMessage")
    @ResponseBody
    public String sendMessage(@RequestBody ChatMessage chatMessage) {
        ResponseResult responseResult = new ResponseResult(false);
        Integer id = chatMessageService.sendChatMessage(chatMessage.getFrom(), chatMessage.getTo(), chatMessage.getMessage());
        if (id == null) {
            responseResult.setMessage("发送消息失败!");
            return JSON.toJSONString(responseResult);
        }
        responseResult.setSuccess(true);
        Map<String, Object> data = new HashMap<>(2);
        data.put("messageId", id);
        data.put("message", chatMessage.getMessage());
        responseResult.setData(data);
        return JSON.toJSONString(responseResult);
    }

    /**
    * 查询和好友的聊天消息
    * @author chengfeng
    * @date 2021/9/10 /0010 23:44
    */
    @RequestMapping("/action/findChatMessageByOne")
    @ResponseBody
    public String findChatMessageByOne(@RequestBody ChatMessage chatMessage) {
        ResponseResult responseResult = new ResponseResult(true);
        //查询消息
        List<ChatMessage> chatMessageByOne = chatMessageService.findChatMessageByOne(chatMessage.getFrom(), chatMessage.getTo());
        responseResult.setData(chatMessageByOne);
        return JSON.toJSONString(responseResult);
    }
}
