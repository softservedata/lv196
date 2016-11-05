package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ChatRepository extends BaseRepository<Chat, Long> {

    @Query("select c from Chat c join c.participants p where c.id = :id and p.email = :email")
    Optional<Chat> findByIdAndParticipantEmail(@Param("id") Long id, @Param("email") String email);
}
