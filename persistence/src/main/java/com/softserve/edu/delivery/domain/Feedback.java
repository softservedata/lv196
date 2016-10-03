package com.softserve.edu.delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    private String text;
    private Integer rate;
    private Boolean approved;
    private Timestamp createdOn;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public Feedback setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Feedback setOrder(Order order) {
        this.order = order;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Feedback setUser(User user) {
        this.user = user;
        return this;
    }

    public String getText() {
        return text;
    }

    public Feedback setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getRate() {
        return rate;
    }

    public Feedback setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public Feedback setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public Feedback setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(feedbackId, feedback.feedbackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId);
    }
}
