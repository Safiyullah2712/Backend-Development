package com.student.management.dto;

public class SendMessageRequest {

    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String messageKey;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(
            String messageKey) {

        this.messageKey = messageKey;
    }
}