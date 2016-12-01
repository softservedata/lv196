package com.softserve.edu.delivery.dto;

import java.util.List;

public class ConversationDto {
    private List<ChatInfoDto> chats;
    private boolean havingMore;

    public ConversationDto(List<ChatInfoDto> chats, boolean havingMore) {
        this.chats = chats;
        this.havingMore = havingMore;
    }

    public ConversationDto() {
    }

    public List<ChatInfoDto> getChats() {
        return chats;
    }

    public ConversationDto setChats(List<ChatInfoDto> chats) {
        this.chats = chats;
        return this;
    }

    public boolean isHavingMore() {
        return havingMore;
    }

    public ConversationDto setHavingMore(boolean havingMore) {
        this.havingMore = havingMore;
        return this;
    }
}
