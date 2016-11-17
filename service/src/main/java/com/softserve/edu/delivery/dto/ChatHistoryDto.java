package com.softserve.edu.delivery.dto;


import java.util.List;

public class ChatHistoryDto {
    private List<ChatMessageDto> messages;
    private boolean havingMore;

    public ChatHistoryDto(List<ChatMessageDto> messages, boolean havingMore) {
        this.messages = messages;
        this.havingMore = havingMore;
    }

    public ChatHistoryDto() {
    }

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public ChatHistoryDto setMessages(List<ChatMessageDto> messages) {
        this.messages = messages;
        return this;
    }

    public boolean isHavingMore() {
        return havingMore;
    }

    public ChatHistoryDto setHavingMore(boolean havingMore) {
        this.havingMore = havingMore;
        return this;
    }
}
