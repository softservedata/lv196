package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.ChatMessage;

public class ChatDto {
    private String driverName;
    private ChatMessageDto message;

    public ChatDto() {
    }

    public ChatDto(String driverName, ChatMessage msg) {
        this.driverName = driverName;
        this.message = ChatMessageDto.of(msg);
    }

    public String getDriverName() {
        return driverName;
    }

    public ChatDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public ChatMessageDto getMessage() {
        return message;
    }

    public ChatDto setMessage(ChatMessageDto msg) {
        this.message = msg;
        return this;
    }
}
