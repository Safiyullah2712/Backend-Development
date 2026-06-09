package com.student.management.service;

import com.student.management.model.Message;
import com.student.management.model.MessageStatus;
import com.student.management.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message sendMessage(
            Integer senderId,
            Integer receiverId,
            String content) {

        Message message =
                new Message();

        message.setSenderId(senderId);

        message.setReceiverId(receiverId);

        message.setContent(content);

        message.setTimestamp(
                LocalDateTime.now());

        message.setStatus(
                MessageStatus.SENT);

        return repository.save(message);
    }

    public List<Message> getChatHistory(
            Integer currentUserId,
            Integer otherUserId) {

        List<Message> messages =
                repository.getConversation(
                        currentUserId,
                        otherUserId);

        for (Message message : messages) {

            if (message.getReceiverId()
                    .equals(currentUserId)
                    &&
                    message.getStatus()
                            == MessageStatus.SENT) {

                message.setStatus(
                        MessageStatus.DELIVERED);

                repository.save(message);
            }
        }

        return repository.getConversation(
                currentUserId,
                otherUserId);
    }

    public String markAsRead(
            Integer receiverId) {

        List<Message> messages =
                repository.findAll();

        for (Message message : messages) {

            if (message.getReceiverId()
                    .equals(receiverId)) {

                message.setStatus(
                        MessageStatus.READ);

                repository.save(message);
            }
        }

        return "Messages marked as READ";
    }
}