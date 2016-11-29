package com.softserve.edu.delivery.domain.chat;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private String authorEmail;
    private String text;
    private boolean seen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public ChatMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public ChatMessage setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public ChatMessage setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public String getText() {
        return text;
    }

    public ChatMessage setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isSeen() {
        return seen;
    }

    public ChatMessage setSeen(boolean seen) {
        this.seen = seen;
        return this;
    }

    public Chat getChat() {
        return chat;
    }

    public ChatMessage setChat(Chat chat) {
        this.chat = chat;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
