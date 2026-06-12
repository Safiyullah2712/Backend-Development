package com.student.management.controller;

import com.student.management.dto.ConversationDTO;
import com.student.management.dto.SendMessageRequest;
import com.student.management.model.Message;
import com.student.management.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(
            MessageService service) {

        this.service = service;
    }

    @PostMapping
    public Message sendMessage(
            @RequestBody
            SendMessageRequest request) {

    	return service.sendMessage(
    	        request.getSenderId(),
    	        request.getReceiverId(),
    	        request.getContent(),
    	        request.getMessageKey());
    }

    @GetMapping("/{user1}/{user2}")
    public List<Message> getChatHistory(
            @PathVariable Integer user1,
            @PathVariable Integer user2) {

        return service.getChatHistory(
                user1,
                user2);
    }

    @PutMapping("/read/{receiverId}/{senderId}")
    public String markAsRead(
            @PathVariable Integer receiverId,
            @PathVariable Integer senderId) {

        return service.markAsRead(
                receiverId,
                senderId);
    }
    
    @GetMapping("/conversations/{userId}")
    public List<ConversationDTO>
    getConversations(
            @PathVariable Integer userId){

        return service.getConversations(
                userId);
    }
}