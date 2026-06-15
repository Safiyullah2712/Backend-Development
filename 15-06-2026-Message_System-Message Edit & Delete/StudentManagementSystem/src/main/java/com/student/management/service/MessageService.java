package com.student.management.service;

import com.student.management.dto.ConversationDTO;
import com.student.management.model.Message;
import com.student.management.model.MessageStatus;
import com.student.management.model.User;
import com.student.management.repository.MessageRepository;
import com.student.management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final UserRepository userRepository;
    
    private static final int EDIT_WINDOW_MINUTES = 15;

    private static final int MAX_RETRY = 3;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(
            MessageRepository repository,
            UserRepository userRepository,
            SimpMessagingTemplate messagingTemplate) {

        this.repository = repository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Message sendMessage(
            Integer senderId,
            Integer receiverId,
            String content,
            String messageKey) {

        Optional<Message> existing =
                repository.findByMessageKey(
                        messageKey);

        if(existing.isPresent()) {

            return existing.get();
        }

        User sender =
                userRepository.findById(senderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sender not found"));

        User receiver =
                userRepository.findById(receiverId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Receiver not found"));

        Message message =
                new Message();

        message.setSender(sender);

        message.setReceiver(receiver);

        message.setContent(content);

        message.setMessageKey(
                messageKey);

        message.setTimestamp(
                LocalDateTime.now());

        message.setStatus(
                MessageStatus.SENT);
        
        Long latest =
                repository.getLatestSequence();

        if(latest == null) {

            latest = 0L;
        }

        message.setSequenceNumber(
                latest + 1);

        Message savedMessage =repository.save(
                message);
        messagingTemplate.convertAndSend(
                "/topic/chat/" + receiverId,
                message);
        return savedMessage;
    }

    public List<Message> getChatHistory(
            Integer currentUserId,
            Integer otherUserId) {

        List<Message> messages =
                repository.getConversation(
                        currentUserId,
                        otherUserId);

        for (Message message : messages) {

            if (message.getReceiver()
                    .getId()
                    .equals(currentUserId)

                    &&

                    message.getStatus()
                            == MessageStatus.SENT) {

                message.setStatus(
                        MessageStatus.DELIVERED);

                repository.save(message);
            }
        }
        
        messages.removeIf(message -> {

            String deleted =
                    message.getDeletedForMe();

            return deleted != null &&
                    Arrays.asList(
                            deleted.split(","))
                            .contains(
                                    currentUserId.toString());
        });

        return messages;
    }

    public String markAsRead(
            Integer receiverId,
            Integer senderId) {

        List<Message> messages =
                repository.findAll();

        for (Message message : messages) {

            if (message.getReceiver()
                    .getId()
                    .equals(receiverId)

                    &&

                    message.getSender()
                            .getId()
                            .equals(senderId)

                    &&

                    message.getStatus()
                            == MessageStatus.DELIVERED) {

                message.setStatus(
                        MessageStatus.READ);

                repository.save(message);
            }
        }

        return "Messages marked as READ";
    }

    public void deliverOfflineMessages(
            Integer receiverId) {

        List<Message> messages =
                repository.findByReceiver_IdAndStatus(
                        receiverId,
                        MessageStatus.SENT);

        for (Message message : messages) {

            message.setStatus(
                    MessageStatus.DELIVERED);

            repository.save(message);
        }
    }

    public void retryDelivery(
            Message message) {

        if(message.getStatus()
                == MessageStatus.DELIVERED) {

            return;
        }

        if(message.getRetryCount()
                < MAX_RETRY) {

            message.setRetryCount(
                    message.getRetryCount()
                            + 1);

            repository.save(
                    message);

        } else {

            message.setFailed(true);

            message.setStatus(
                    MessageStatus.FAILED);

            repository.save(
                    message);
        }
    }

    public List<ConversationDTO> getConversations(
            Integer currentUserId) {

        List<Message> messages =
                repository.findAll();

        Map<Integer, ConversationDTO> conversationMap =
                new HashMap<>();

        for (Message message : messages) {

            if (message.getSender() == null ||
                message.getReceiver() == null) {

                continue;
            }

            Integer otherUserId;

            if (message.getSender()
                    .getId()
                    .equals(currentUserId)) {

                otherUserId =
                        message.getReceiver()
                               .getId();

            } else if (message.getReceiver()
                    .getId()
                    .equals(currentUserId)) {

                otherUserId =
                        message.getSender()
                               .getId();

            } else {

                continue;
            }

            long unreadCount =
                    repository.countUnreadMessages(
                            currentUserId,
                            otherUserId);

            conversationMap.put(
                    otherUserId,
                    new ConversationDTO(
                            otherUserId,
                            message.getContent(),
                            unreadCount));
        }

        return new ArrayList<>(
                conversationMap.values());
    }
    
    public Message editMessage(
            Long messageId,
            Integer senderId,
            String newContent) {

        Message message =
                repository.findById(messageId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Message not found"));

        if (!message.getSender()
                .getId()
                .equals(senderId)) {

            throw new RuntimeException(
                    "Only sender can edit");
        }

        if (message.getDeleted()) {

            throw new RuntimeException(
                    "Message already deleted");
        }

        LocalDateTime limit =
                message.getTimestamp()
                        .plusMinutes(
                                EDIT_WINDOW_MINUTES);

        if (LocalDateTime.now()
                .isAfter(limit)) {

            throw new RuntimeException(
                    "Edit window expired");
        }

        message.setContent(
                newContent);

        message.setEdited(true);

        message.setUpdatedAt(
                LocalDateTime.now());

        Message updatedMessage = repository.save(
                message);
        messagingTemplate.convertAndSend(
                "/topic/chat/" +
                        message.getReceiver().getId(),updatedMessage);
        return updatedMessage;
    }
    
    public Message deleteForEveryone(
            Long messageId,
            Integer senderId) {

        Message message =
                repository.findById(messageId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Message not found"));
        
        if(message.getDeleted()){
            return message;
        }
        
        if (!message.getSender()
                .getId()
                .equals(senderId)) {

            throw new RuntimeException(
                    "Only sender can delete");
        }

        if (message.getDeleted()) {

            return message;
        }

        message.setDeleted(true);

        message.setContent(
                "This message was deleted");

        message.setUpdatedAt(
                LocalDateTime.now());

        Message deletedMessage =
                repository.save(message);

        messagingTemplate.convertAndSend(
                "/topic/chat/" +
                        message.getReceiver().getId(),
                deletedMessage);

        return deletedMessage;
    }
    
    public String deleteForMe(
            Long messageId,
            Integer userId) {

        Message message =
                repository.findById(messageId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Message not found"));

        String existing =
                message.getDeletedForMe();

        if (existing == null) {

            existing =
                    String.valueOf(userId);

        } else {

            List<String> deletedUsers =
                    Arrays.asList(
                            existing.split(","));

            if (!deletedUsers.contains(
                    String.valueOf(userId))) {

                existing =
                        existing + "," + userId;
            }
        }

        message.setDeletedForMe(
                existing);

        repository.save(
                message);

        return "Deleted for me";
    }
}