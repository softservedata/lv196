package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.ChatMessageDto;
import com.softserve.edu.delivery.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.AUTHENTICATED;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{chatId}")
    public void handleMessage(@DestinationVariable("chatId") Long chatId,
                              @Payload ChatMessageDto dto, Principal principal) {

        dto = chatService.saveMessage(dto.setChatId(chatId)
                .setAuthorEmail(principal.getName())
                .setTimestamp(new Timestamp(new Date().getTime())));
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, dto);


        String receiver = chatService.findReceiverEmail(chatId, dto.getAuthorEmail());
        messagingTemplate.convertAndSendToUser(receiver, "/queue/chat-notifications", dto.getChatId().toString());
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "/chat/{chatId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ChatMessageDto>> chatHistory(@PathVariable Long chatId, Principal principal) {
        return chatService
                .findByIdAndParticipant(chatId, principal.getName())
                .map(chat -> new ResponseEntity<>(chatService.findMessagesHistory(chatId), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED));
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "/chat/{chatId}", method = RequestMethod.POST)
    @ResponseBody
    public void createConversation(@PathVariable Long chatId, Principal principal) {
        chatService.initChat(chatId, principal.getName());
    }
}

