package com.chengfeng.study.myspringbootproject.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * ChatMessage class
 * 聊天消息
 * @author chengfeng
 * @date 2021/8/30 /0030 21:49
 */
@Entity(name = "chat_message")
public class ChatMessage {
    private Integer id;
    private String from;
    private String to;
    private String message;
    private String sendTime;

    public ChatMessage() {
    }

    public ChatMessage(Integer id, String from, String to, String message, String sendTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.sendTime = sendTime;
    }
    public ChatMessage(String from, String to, String message, String sendTime) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.sendTime = sendTime;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return id == that.id && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(message, that.message) && Objects.equals(sendTime, that.sendTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, message, sendTime);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }

}
