package com.student.management.repository;

import com.student.management.model.Message;
import com.student.management.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("""
            SELECT m
            FROM Message m
            WHERE
            (m.senderId = :user1 AND m.receiverId = :user2)
            OR
            (m.senderId = :user2 AND m.receiverId = :user1)
            ORDER BY m.timestamp ASC
            """)
    List<Message> getConversation(
            Integer user1,
            Integer user2
    );

    List<Message> findByReceiverId(
            Integer receiverId
    );

    List<Message> findBySenderId(
            Integer senderId
    );

    List<Message> findByReceiverIdAndStatus(
            Integer receiverId,
            MessageStatus status
    );

    List<Message> findBySenderIdAndReceiverId(
            Integer senderId,
            Integer receiverId
    );
    
    @Query("""
    		SELECT COUNT(m)
    		FROM Message m
    		WHERE m.receiverId = :receiverId
    		AND m.status = 'DELIVERED'
    		""")
    		long countUnreadMessages(
    		        Integer receiverId);
    

}