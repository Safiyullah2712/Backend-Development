package com.student.management.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer senderId;

    private Integer receiverId;

    private String content;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    public Message() {
    }

    public Message(Long id,
            Integer senderId,
            Integer receiverId,
            String content,
            LocalDateTime timestamp,
            MessageStatus status) {

			 this.id = id;
			 this.senderId = senderId;
			 this.receiverId = receiverId;
			 this.content = content;
			 this.timestamp = timestamp;
			 this.status = status;
			}
			
			public Long getId() {
			 return id;
			}
			
			public void setId(Long id) {
			 this.id = id;
			}
			
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
			
			public LocalDateTime getTimestamp() {
			 return timestamp;
			}
			
			public void setTimestamp(LocalDateTime timestamp) {
			 this.timestamp = timestamp;
			}
			
			public MessageStatus getStatus() {
			 return status;
			}
			
			public void setStatus(MessageStatus status) {
			 this.status = status;
			}
}
