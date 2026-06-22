package com.student.management.controller;

import com.student.management.dto.ConversationDTO;

import com.student.management.dto.EditMessageRequest;
import com.student.management.dto.MessageSearchDTO;
import com.student.management.dto.SendMessageRequest;
import com.student.management.model.Message;
import com.student.management.service.MessageService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    
    @PutMapping("/{messageId}")
    public Message editMessage(
            @PathVariable Long messageId,
            @RequestBody EditMessageRequest request) {

        return service.editMessage(
                messageId,
                request.getSenderId(),
                request.getContent());
    }
    
    @DeleteMapping("/{messageId}/everyone")
    public Message deleteForEveryone(
            @PathVariable Long messageId,
            @RequestParam Integer senderId) {

        return service.deleteForEveryone(
                messageId,
                senderId);
    }
    
    @DeleteMapping("/{messageId}/me")
    public String deleteForMe(
            @PathVariable Long messageId,
            @RequestParam Integer userId) {

        return service.deleteForMe(
                messageId,
                userId);
    }	
    
    @GetMapping("/sync")
    public List<Message> syncMessages(
            @RequestParam Integer userId,
            @RequestParam Long lastMessageId) {

        return service.syncMessages(
                userId,
                lastMessageId);
    }
    
    @GetMapping("/search")
    public Page<MessageSearchDTO> searchMessages(

            @RequestParam String q,

            @RequestParam(
                    defaultValue = "0")
            int page,

            @RequestParam(
                    defaultValue = "10")
            int size) {

        return service.searchMessages(
                q,
                page,
                size);
    }
    
    @GetMapping("/search/date")
    public Page<MessageSearchDTO> searchMessagesByDate(

            @RequestParam String q,

            @RequestParam String fromDate,

            @RequestParam String toDate,

            @RequestParam(
                    defaultValue = "0")
            int page,

            @RequestParam(
                    defaultValue = "10")
            int size) {

        return service.searchMessages(
                q,
                page,
                size);
    }
}