package com.xxx.yeb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 在线聊天消息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMessage {
    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String formNickName;
}
