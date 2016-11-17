package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.Chat;
import com.softserve.edu.delivery.dto.ChatDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ChatRepository extends BaseRepository<Chat, Long> {

    @Query("select new com.softserve.edu.delivery.dto.ChatDto(" +
            "concat(off.car.driver.firstName, ' ', off.car.driver.lastName), msg) " +
            "from ChatMessage msg join msg.chat c join c.participants p join c.offer off " +
            "where p.email = :email and msg.timestamp = " +
            "(select max(timestamp) from ChatMessage m2 where m2.chat = msg.chat)")
    List<ChatDto> findChatsByParticipantEmail(@Param("email") String email);

    @Query("select c from Chat c join c.participants p where c.id = :id and p.email = :email")
    Optional<Chat> findByIdAndParticipantEmail(@Param("id") Long id, @Param("email") String email);

    @Query("select p.email from Chat c join c.participants p where c.id = :chatId and p.email <> :senderEmail")
    String findReceiverEmail(@Param("chatId") Long chatId, @Param("senderEmail") String senderEmail);
}
