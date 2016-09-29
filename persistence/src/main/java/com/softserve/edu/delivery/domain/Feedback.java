package com.softserve.edu.delivery.domain;

import javax.persistence.*;

/**
 * @author Ivan Rudnytskyi, 11.09.2016.
 */
@Entity
@Table(name = "FEEDBACK")
public class Feedback {

    public Feedback() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "email")
    private User user;
    @Column(name = "rate")
    private Integer rate;
    @Column(name = "approved")
    private Boolean approved;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        return feedbackId.equals(feedback.feedbackId);

    }

    @Override
    public int hashCode() {
        return feedbackId.hashCode();
    }
}
