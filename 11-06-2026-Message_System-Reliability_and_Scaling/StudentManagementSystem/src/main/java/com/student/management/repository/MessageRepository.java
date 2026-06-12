package com.student.management.repository;

import com.student.management.model.Message;
import com.student.management.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository
        extends JpaRepository<Message, Long> {

	@Query("""
			SELECT m
			FROM Message m
			WHERE
			(m.sender.id = :user1 AND m.receiver.id = :user2)
			OR
			(m.sender.id = :user2 AND m.receiver.id = :user1)
			ORDER BY m.timestamp ASC
			""")
			List<Message> getConversation(
			        Integer user1,
			        Integer user2
			);

    List<Message> findByReceiver_Id(
            Integer receiverId
    );

    List<Message> findBySender_Id(
            Integer senderId
    );

    List<Message> findByReceiver_IdAndStatus(
            Integer receiverId,
            MessageStatus status
    );

    List<Message> findBySender_IdAndReceiver_Id(
            Integer senderId,
            Integer receiverId
    );

    @Query("""
            SELECT COUNT(m)
            FROM Message m
            WHERE
            m.receiver.id = :receiverId
            AND
            m.sender.id = :senderId
            AND
            m.status <> 'READ'
            """)
    long countUnreadMessages(
            Integer receiverId,
            Integer senderId
    );
}