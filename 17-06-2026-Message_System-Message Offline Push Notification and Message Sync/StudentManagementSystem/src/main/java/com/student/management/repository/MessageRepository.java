package com.student.management.repository;

import com.student.management.model.Message;
import com.student.management.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository
        extends JpaRepository<Message, Long> {

	@Query("""
			SELECT m
			FROM Message m
			WHERE
			(m.sender.id = :user1
			AND m.receiver.id = :user2)

			OR

			(m.sender.id = :user2
			AND m.receiver.id = :user1)

			ORDER BY m.sequenceNumber ASC
			""")
			List<Message> getConversation(
			        @Param("user1") Integer user1,
			        @Param("user2") Integer user2);

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
    
    Optional<Message> findByMessageKey(
            String messageKey);

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
    
    @Query("""
    	       SELECT MAX(m.sequenceNumber)
    	       FROM Message m
    	       """)
    	Long getLatestSequence();
    
    @Query("""
    		SELECT m
    		FROM Message m
    		WHERE
    		(
    		m.sender.id = :userId
    		OR
    		m.receiver.id = :userId
    		)
    		AND
    		m.id > :lastMessageId
    		ORDER BY m.sequenceNumber
    		""")
    		List<Message> syncMessages(
    		        Integer userId,
    		        Long lastMessageId);
}