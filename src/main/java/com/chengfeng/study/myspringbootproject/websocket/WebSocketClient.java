package com.chengfeng.study.myspringbootproject.websocket;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WebSocketClient class
 *
 * @author chengfeng
 * @date 2021/2/28 /0028 19:11
 */
@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocketClient {
    private static final Logger log = Logger.getLogger(WebSocketClient.class.getName());

    /**
     * 标识当前连接客户端的用户名
     */
    private String name;
    /**
     * 与某个客户端的连接对话, 需要通过它来给客户端发送消息
     */
    private Session session;

    /**
     * 用于存所有连接服务的客户端, 这个存储对象是安全的
     */
    private static final ConcurrentHashMap<String, WebSocketClient> webSocketSet = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String name) {
        this.session = session;
        this.name = name;
        webSocketSet.put(name, this);
        log.log(Level.INFO, "[WebSocket] [" + name + "]连接成功，当前连接人数为：" + webSocketSet.size() +
                ", 当前连接: " + webSocketSet);
        ResponseResult success = ResultUtil.success();
        success.setMessage(name + ",服务器连接成功~");
        appointSending(name, JSON.toJSONString(success));
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this.name);
        log.log(Level.INFO,  " [WebSocket] [" + this.name + "]退出成功，当前连接人数为：" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String messageStrObj, Session session) {
        JSONObject messageObj = JSONUtil.parseObj(messageStrObj);
        log.log(Level.INFO, "[WebSocket] 收到消息: " + messageStrObj);
        String message = messageObj.get("message").toString();
        String from = messageObj.get("from").toString();
        try {
            Object to = messageObj.get("to");
            log.log(Level.INFO, "[" + from + "] 发送给 [" + to + "], 消息: " + message);
            if (to != null) {
                //指定发给某人
                if (!webSocketSet.containsKey(to.toString())) {
                    //不在线
                    log.log(Level.INFO, "【websocket】: send failure! " + to + " is offline!!!");
                    appointSending(from, to + " is offline!!!");
                    return;
                }
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("from", from);
                messageMap.put("message", message);
                appointSending(to.toString(), JSON.toJSONString(messageMap));
            } else {
                //群发消息
                groupSending(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            appointSending(from, "消息发送失败!");
        }

    }

    /**
     * @description 群发消息
     * @author chengfeng
     * @date 2021/3/1 /0001 21:08
     **/
    private void groupSending(String message) {
        for (String name : webSocketSet.keySet()) {
            try {
                webSocketSet.get(name).session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.log(Level.WARNING, "【websocket】群发消息失败。。。");
                e.printStackTrace();
            }
        }
    }

    /**
     * @description 单发消息
     * @author chengfeng
     * @date 2021/3/1 /0001 21:08
     **/
    public static boolean appointSending(String name, String message) {
        try {
            log.log(Level.INFO, "websocket send message: " + message);
            if (!webSocketSet.containsKey(name)) {
                log.log(Level.WARNING, "【websocket】: send failure! " + name + " is offline!!!");
                return false;
            }
            webSocketSet.get(name).session.getBasicRemote().sendText(message);
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "【websocket】发送消息失败。。。");
            e.printStackTrace();
            return false;
        }
    }

    /**
    * 聊天发送消息
    * @author chengfeng
    * @date 2021/8/30 /0030 21:46
    */
    public boolean chatSendMessage(String from, String to, String message) {
        if (!webSocketSet.containsKey(to)) {
            //不在线
            log.log(Level.WARNING, "【websocket】: send failure! " + to + " is offline!!!");
            return appointSending(from, to + " is offline!!!");
        }
        Map<String, Object> messageMap = new HashMap<>(2);
        messageMap.put("from", from);
        messageMap.put("message", message);
        messageMap.put("type", "chat");
        return appointSending(to, JSON.toJSONString(messageMap));
    }

}
