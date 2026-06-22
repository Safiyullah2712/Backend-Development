package com.student.management.dto;

public class ConversationDTO {

    private Integer userId;

    private String lastMessage;

    private long unreadCount;

    public ConversationDTO() {
    }

    public ConversationDTO(
            Integer userId,
            String lastMessage,
            long unreadCount) {

        this.userId = userId;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public long getUnreadCount() {
        return unreadCount;
    }
}