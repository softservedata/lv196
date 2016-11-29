package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends BaseRepository<ChatMessage, Long> {
    Page<ChatMessage> findByChatIdOrderByTimestampDesc(Long chatId, Pageable pageable);
    List<ChatMessage> findByIdIn(List<Long> ids);

    @Query("select count(m) from ChatMessage m join m.chat c join c.participants p " +
            "where p.email = :email and m.seen = 0 and m.authorEmail <> :email")
    int countUnreadMessages(@Param("email") String email);
}
