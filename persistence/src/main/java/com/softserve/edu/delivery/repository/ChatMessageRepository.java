package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatMessageRepository extends BaseRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatId(Long chatId);
    Page<ChatMessage> findByChatIdOrderByTimestampDesc(Long chatId, Pageable pageable);
}
