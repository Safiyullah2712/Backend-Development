package com.student.management.dto;

import java.time.LocalDateTime;

public class MessageSearchDTO {

    private Long messageId;

    private String content;

    private String sender;

    private LocalDateTime timestamp;

    public MessageSearchDTO(
            Long messageId,
            String content,
            String sender,
            LocalDateTime timestamp) {

        this.messageId = messageId;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public Long getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}