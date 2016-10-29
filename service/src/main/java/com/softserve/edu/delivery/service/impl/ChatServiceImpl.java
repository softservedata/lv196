package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.chat.ChatMessage;
import com.softserve.edu.delivery.domain.chat.Conversation;
import com.softserve.edu.delivery.dto.ChatMessageDto;
import com.softserve.edu.delivery.repository.ChatMessageRepository;
import com.softserve.edu.delivery.repository.ConversationRepository;
import com.softserve.edu.delivery.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public Conversation findByIdAndParticipant(Long id, String email) {
        return conversationRepository
                .findByIdAndParticipantEmail(id, email)
                .orElseThrow(() -> new IllegalArgumentException("No such conversation for this user"));
    }

    @Override
    @Transactional
    public ChatMessageDto saveMessage(ChatMessageDto dto) {
        Conversation conversation = findByIdAndParticipant(dto.getConversationId(), dto.getAuthorEmail());
        ChatMessage chatMessage = new ChatMessage()
                .setAuthorEmail(dto.getAuthorEmail())
                .setConversation(conversation)
                .setText(dto.getText())
                .setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : new Timestamp(new Date().getTime()));

        chatMessage = chatMessageRepository.save(chatMessage);

        return dto.setId(chatMessage.getId());
    }

    @Override
    public List<ChatMessageDto> findMessagesHistory(Long conversationId) {
        List<ChatMessage> messages = chatMessageRepository.findByConversationId(conversationId);

        return messages.stream()
                .map(m -> new ChatMessageDto()
                        .setId(m.getId())
                        .setConversationId(conversationId)
                        .setAuthorEmail(m.getAuthorEmail())
                        .setTimestamp(m.getTimestamp())
                        .setText(m.getText()))
                .collect(Collectors.toList());
    }
}
