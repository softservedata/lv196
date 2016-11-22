package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.chat.Chat;
import com.softserve.edu.delivery.dto.ChatHistoryDto;
import com.softserve.edu.delivery.dto.ChatMessageDto;
import com.softserve.edu.delivery.dto.ConversationDto;
import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;

public interface ChatService {
    /**
     * Finds a chat by given id
     * and check if it is applicable for given user email
     *
     * @param id chat id
     * @param email user email
     * @return optional of chat entity
     */
    Optional<Chat> findByIdAndParticipant(Long id, String email);

    /**
     * Finds portion conversations by given email
     *
     * @param email user email
     * @param page page of requested chats
     * @param size size of a portion
     * @return list of all conversations
     */
    ConversationDto findByParticipant(String email, int page, int size);

    /**
     * Persists message retrieved in dto
     *
     * @param dto message dto
     * @return message dto with generated id
     * @throws IllegalArgumentException if could not find chat or given user has no access to it
     */
    ChatMessageDto saveMessage(ChatMessageDto dto);

    /**
     * Finds portion messages for given chat and page params
     *
     * @param chatId id of chat
     * @param page page of requested messages
     * @param size size of a portion
     * @return list of all messages in chat
     */
    ChatHistoryDto findMessagesHistory(Long chatId, int page, int size);

    /**
     * Creates chat for given offer id
     *
     * @param offerId id of offer
     * @param email user email
     * @throws IllegalArgumentException if retrieved data is unappropriated for chat creation
     * @throws AccessDeniedException if user doesn' have access to this chat
     */
    void initChat(Long offerId, String email);

    /**
     * Finds chat companion
     *
     * @param chatId id of chat
     * @param senderEmail another chat companion, the sender email
     * @return receiver email
     */
    String findReceiverEmail(Long chatId, String senderEmail);
}
