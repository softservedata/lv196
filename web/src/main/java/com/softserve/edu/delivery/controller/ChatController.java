package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.ChatMessageDto;
import com.softserve.edu.delivery.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChatService chatService;


    @MessageMapping("/chat/{conversationId}")
    public ChatMessageDto handleMessage(@DestinationVariable("conversationId") Long conversationId,
                                        @Payload ChatMessageDto dto, Principal principal) {
        dto.setConversationId(conversationId).setAuthorEmail(principal.getName());

        return chatService.saveMessage(dto);
    }

    @RequestMapping(path = "/chat/{conversationId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageDto> chatHistory(@PathVariable Long conversationId) {
        return chatService.findMessagesHistory(conversationId);
    }
}

