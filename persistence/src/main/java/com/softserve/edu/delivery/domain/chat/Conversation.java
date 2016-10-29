package com.softserve.edu.delivery.domain.chat;

import com.softserve.edu.delivery.domain.User;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapKey
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "conversation_participant",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_email"))
    private Map<String, User> participants;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages;

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public Conversation setId(Long id) {
        this.id = id;
        return this;
    }

    public Map<String, User> getParticipants() {
        return participants;
    }

    public Conversation setParticipants(Map<String, User> participants) {
        this.participants = participants;
        return this;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public Conversation setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
