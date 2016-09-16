package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

import javax.persistence.*;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public class FeedbackDTO {

    public FeedbackDTO() {
    }

    private Long feedbackId;
    private Order order;
    private String text;
    private User user;
    private Integer rate;
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

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
