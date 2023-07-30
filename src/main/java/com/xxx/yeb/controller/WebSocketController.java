package com.xxx.yeb.controller;

import com.xxx.yeb.entity.Admin;
import com.xxx.yeb.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * webSocket在线聊天
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMessage){
        Admin admin = (Admin)authentication.getPrincipal();
        chatMessage.setFrom(admin.getUsername());
        chatMessage.setFormNickName(admin.getName());
        chatMessage.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(),"/queue/chat", chatMessage);
    }
}
