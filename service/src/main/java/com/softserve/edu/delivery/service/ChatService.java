package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.chat.Chat;
import com.softserve.edu.delivery.dto.ChatMessageDto;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
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
     * Persists message retrieved in dto
     *
     * @param dto message dto
     * @return message dto with generated id
     * @throws IllegalArgumentException if could not find chat or given user has no access to it
     */
    ChatMessageDto saveMessage(ChatMessageDto dto);

    /**
     * Finds all messages for given chat
     *
     * @param chatId id of chat
     * @return list of all messages in chat
     * @throws IllegalArgumentException if chat is not created yet or this user doesn't have access to this
     */
    List<ChatMessageDto> findMessagesHistory(Long chatId);

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
