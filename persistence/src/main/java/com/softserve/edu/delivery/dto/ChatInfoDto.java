package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.ChatMessage;

public class ChatInfoDto {
    private String companion;
    private ChatMessageDto message;
    private boolean seen;

    public ChatInfoDto() {
    }

    public ChatInfoDto(String companion, ChatMessage msg) {
        this.companion = companion;
        this.message = ChatMessageDto.of(msg);
        this.seen = msg.isSeen();
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

    public boolean isSeen() {
        return seen;
    }

    public ChatInfoDto setSeen(boolean seen) {
        this.seen = seen;
        return this;
    }
}
