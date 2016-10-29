package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.chat.Conversation;
import com.softserve.edu.delivery.dto.ChatMessageDto;

import java.util.List;

public interface ChatService {
    Conversation findByIdAndParticipant(Long id, String email);
    ChatMessageDto saveMessage(ChatMessageDto dto);
    List<ChatMessageDto> findMessagesHistory(Long conversationId);
}
