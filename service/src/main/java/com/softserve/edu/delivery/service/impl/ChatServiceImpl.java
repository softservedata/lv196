package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.domain.chat.Chat;
import com.softserve.edu.delivery.domain.chat.ChatMessage;
import com.softserve.edu.delivery.dto.ChatHistoryDto;
import com.softserve.edu.delivery.dto.ChatInfoDto;
import com.softserve.edu.delivery.dto.ChatMessageDto;
import com.softserve.edu.delivery.dto.ConversationDto;
import com.softserve.edu.delivery.repository.ChatMessageRepository;
import com.softserve.edu.delivery.repository.ChatRepository;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Optional<Chat> findByIdAndParticipant(Long id, String email) {
        return chatRepository.findByIdAndParticipantEmail(id, email);
    }

    @Override
    public ConversationDto findByParticipant(String email, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "timestamp");

        Page<ChatInfoDto> chatPage = chatRepository.findChatsByParticipantEmail(email, pageable);

        return new ConversationDto(chatPage.getContent(), chatPage.hasNext());
    }

    @Override
    @Transactional
    public ChatMessageDto saveMessage(ChatMessageDto dto) {
        Chat chat = findByIdAndParticipant(dto.getChatId(), dto.getAuthorEmail())
                .orElseThrow(() -> new AccessDeniedException("This user have no access to this conversation"));

        ChatMessage chatMessage = new ChatMessage()
                .setAuthorEmail(dto.getAuthorEmail())
                .setChat(chat)
                .setText(dto.getText())
                .setTimestamp(dto.getTimestamp());

        chatMessage = chatMessageRepository.save(chatMessage);

        return dto.setId(chatMessage.getId());
    }

    @Override
    public String findReceiverEmail(Long chatId, String senderEmail) {
        return chatRepository.findReceiverEmail(chatId, senderEmail);
    }

    @Override
    public ChatHistoryDto findMessagesHistory(Long chatId, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "timestamp");

        Page<ChatMessage> chatPage = chatMessageRepository.findByChatId(chatId, pageable);

        List<ChatMessageDto> messages = chatPage.getContent()
                .stream()
                .map(ChatMessageDto::of)
                .collect(Collectors.toList());

        return new ChatHistoryDto(messages, chatPage.hasNext());
    }

    @Override
    @Transactional
    public void initChat(Long offerId, String email) {
        Offer offer = offerRepository.findOneOpt(offerId)
                .orElseThrow(() -> new IllegalArgumentException("No such offer: " + offerId));

        User customer = Optional.ofNullable(offer.getOrder())
                .map(Order::getCustomer)
                .orElseThrow(() -> new IllegalArgumentException("Could not create conversation without customer"));

        User driver = Optional.ofNullable(offer.getCar())
                .map(Car::getDriver)
                .orElseThrow(() -> new IllegalArgumentException("Could not create conversation without driver"));

        if (!customer.getEmail().equals(email) && !driver.getEmail().equals(email)) {
            throw new AccessDeniedException("This user have no access to create this conversation");
        }

        Chat chat = new Chat()
                .setId(offerId)
                .setOffer(offer)
                .setParticipants(Arrays.asList(customer, driver));

        chatRepository.save(chat);
    }
}
