package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.chat.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends BaseRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatId(Long chatId);
}
