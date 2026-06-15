package com.student.management.dto;

public class EditMessageRequest {

    private Integer senderId;

    private String content;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(
            Integer senderId) {

        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content) {

        this.content = content;
    }
}