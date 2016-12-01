package com.softserve.edu.delivery.dto;


import com.softserve.edu.delivery.domain.chat.ChatMessage;

import java.util.Objects;

public class ChatMessageDto {
    private Long id;
    private Long chatId;
    private String authorEmail;
    private Long timestamp;
    private String text;
    private boolean seen;

    public ChatMessageDto() {
    }

    public static ChatMessageDto of(ChatMessage msg) {
        Objects.requireNonNull(msg, "No message found");
        return new ChatMessageDto()
                .setId(msg.getId())
                .setAuthorEmail(msg.getAuthorEmail())
                .setChatId(msg.getChat() == null ? null : msg.getChat().getId())
                .setText(msg.getText())
                .setTimestamp(msg.getTimestamp())
                .setSeen(msg.isSeen());
    }

    public Long getId() {
        return id;
    }

    public ChatMessageDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getChatId() {
        return chatId;
    }

    public ChatMessageDto setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public ChatMessageDto setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public ChatMessageDto setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getText() {
        return text;
    }

    public ChatMessageDto setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isSeen() {
        return seen;
    }

    public ChatMessageDto setSeen(boolean seen) {
        this.seen = seen;
        return this;
    }
}
