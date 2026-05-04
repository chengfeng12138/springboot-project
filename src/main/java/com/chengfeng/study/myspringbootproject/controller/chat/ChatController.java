package com.chengfeng.study.myspringbootproject.controller.chat;

import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.common.ResultEnum;
import com.chengfeng.study.myspringbootproject.config.moderation.ChatModerationProperties;
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
    @Autowired
    ChatModerationProperties chatModerationProperties;

    /**
    * 发送聊天消息
    * @author chengfeng
    * @date 2021/8/30 /0030 22:09
    */
    @RequestMapping("/action/sendChatMessage")
    @ResponseBody
    public String sendMessage(@RequestBody ChatMessage chatMessage) {
        ResponseResult responseResult = new ResponseResult(false);
        String msg = chatMessage.getMessage();
        if (msg == null || msg.trim().isEmpty()) {
            responseResult.setCode(ResultEnum.DATA_IS_NULL.getCode());
            responseResult.setMessage("消息内容为空!");
            return JSON.toJSONString(responseResult);
        }
        if (!isCompliant(msg)) {
            responseResult.setCode(ResultEnum.CONTENT_ILLEGAL.getCode());
            responseResult.setMessage("消息内容违规!");
            return JSON.toJSONString(responseResult);
        }
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

    private boolean isCompliant(String message) {
        String text = message.trim();
        Boolean enabled = chatModerationProperties.getEnabled();
        if (enabled != null && !enabled) return true;
        Integer maxLength = chatModerationProperties.getMaxLength();
        if (maxLength != null && text.length() > maxLength) return false;
        java.util.List<String> bannedWords = chatModerationProperties.getBannedWords();
        if (bannedWords == null || bannedWords.isEmpty()) {
            bannedWords = java.util.Arrays.asList("暴力","恐怖","黄赌毒","辱骂","仇恨","涉政","反动","诈骗","非法","成人内容","淫秽");
        }
        for (String w : bannedWords) {
            if (text.contains(w)) return false;
        }
        return true;
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
