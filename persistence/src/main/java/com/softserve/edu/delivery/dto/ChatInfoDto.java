package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.ChatMessage;

public class ChatInfoDto {
    private String driverName;
    private ChatMessageDto message;

    public ChatInfoDto() {
    }

    public ChatInfoDto(String driverName, ChatMessage msg) {
        this.driverName = driverName;
        this.message = ChatMessageDto.of(msg);
    }

    public String getDriverName() {
        return driverName;
    }

    public ChatInfoDto setDriverName(String driverName) {
        this.driverName = driverName;
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
