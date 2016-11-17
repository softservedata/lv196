package com.softserve.edu.delivery.dto;


import com.softserve.edu.delivery.domain.chat.ChatMessage;

import java.sql.Timestamp;
import java.util.Objects;

public class ChatMessageDto {
    private Long id;
    private Long chatId;
    private String authorEmail;
    private Timestamp timestamp;
    private String text;

    public ChatMessageDto() {
    }

    public static ChatMessageDto of(ChatMessage msg) {
        Objects.requireNonNull(msg, "No message found");
        return new ChatMessageDto()
                .setId(msg.getId())
                .setAuthorEmail(msg.getAuthorEmail())
                .setChatId(msg.getChat() == null ? null : msg.getChat().getId())
                .setText(msg.getText())
                .setTimestamp(msg.getTimestamp());
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public ChatMessageDto setTimestamp(Timestamp timestamp) {
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

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "chatId=" + chatId +
                ", authorEmail='" + authorEmail + '\'' +
                ", timestamp=" + timestamp +
                ", text='" + text + '\'' +
                '}';
    }
}
