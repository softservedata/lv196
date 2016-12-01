package com.softserve.edu.delivery.domain.chat;

import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    // It is used the same as offer id
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_participant",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_email"))
    private List<User> participants;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> messages;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id", unique = true)
    private Offer offer;

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public Chat setId(Long id) {
        this.id = id;
        return this;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public Chat setParticipants(List<User> participants) {
        this.participants = participants;
        return this;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public Chat setMessages(List<ChatMessage> messages) {
        this.messages = messages;
        return this;
    }

    public Offer getOffer() {
        return offer;
    }

    public Chat setOffer(Offer offer) {
        this.offer = offer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat that = (Chat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
