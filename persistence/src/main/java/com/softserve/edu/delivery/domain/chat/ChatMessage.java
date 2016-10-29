package com.softserve.edu.delivery.domain.chat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp timestamp;
    private String authorEmail;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public ChatMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public ChatMessage setTimestamp(Timestamp timestamp) {
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

    public Conversation getConversation() {
        return conversation;
    }

    public ChatMessage setConversation(Conversation conversation) {
        this.conversation = conversation;
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
