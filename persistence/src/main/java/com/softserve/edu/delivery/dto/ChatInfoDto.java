package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.ChatMessage;

public class ChatInfoDto {
    private String companion;
    private ChatMessageDto message;

    public ChatInfoDto() {
    }

    public ChatInfoDto(String companion, ChatMessage msg) {
        this.companion = companion;
        this.message = ChatMessageDto.of(msg);
    }

    public String getCompanion() {
        return companion;
    }

    public ChatInfoDto setCompanion(String companion) {
        this.companion = companion;
        return this;
    }

    public ChatMessageDto getMessage() {
        return message;
    }

    public ChatInfoDto setMessage(ChatMessageDto msg) {
        this.message = msg;
        return this;
    }
}
