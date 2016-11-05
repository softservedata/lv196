package com.softserve.edu.delivery.dto;


import java.sql.Timestamp;

public class ChatMessageDto {
    private Long id;
    private Long chatId;
    private String authorEmail;
    private Timestamp timestamp;
    private String text;

    public ChatMessageDto() {
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
