package com.chengfeng.study.myspringbootproject.controller.chat;

import com.chengfeng.study.myspringbootproject.pojo.ChatMessage;
import com.chengfeng.study.myspringbootproject.service.chat.ChatMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatMessageService chatMessageService;

    @Test
    public void shouldRejectIllegalContent() throws Exception {
        String body = "{\"from\":\"alice\",\"to\":\"bob\",\"message\":\"包含暴力内容\"}";
        mockMvc.perform(post("/action/sendChatMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(405))
                .andExpect(jsonPath("$.message").value("消息内容违规!"));
    }

    @Test
    public void shouldRejectEmptyContent() throws Exception {
        String body = "{\"from\":\"alice\",\"to\":\"bob\",\"message\":\"   \"}";
        mockMvc.perform(post("/action/sendChatMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(3))
                .andExpect(jsonPath("$.message").value("消息内容为空!"));
    }

    @Test
    public void shouldPassValidContent() throws Exception {
        given(chatMessageService.sendChatMessage(eq("alice"), eq("bob"), any()))
                .willReturn(123);
        String body = "{\"from\":\"alice\",\"to\":\"bob\",\"message\":\"你好\"}";
        mockMvc.perform(post("/action/sendChatMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.messageId").value(123))
                .andExpect(jsonPath("$.data.message").value("你好"));
    }
}
