package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.Conversation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ConversationRepository extends BaseRepository<Conversation, Long> {

    @Query("from Conversation c join c.participants p where c.id = :id and p.email = :email")
    Optional<Conversation> findByIdAndParticipantEmail(@Param("id") Long id, @Param("email") String email);
}
