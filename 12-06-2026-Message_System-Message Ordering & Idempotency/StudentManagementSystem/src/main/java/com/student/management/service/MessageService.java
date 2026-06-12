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

@Service
public class MessageService {

    private final MessageRepository repository;
    private final UserRepository userRepository;

    private static final int MAX_RETRY = 3;

    public MessageService(
            MessageRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
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

        return repository.save(
                message);
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

        return repository.getConversation(
                currentUserId,
                otherUserId);
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
}