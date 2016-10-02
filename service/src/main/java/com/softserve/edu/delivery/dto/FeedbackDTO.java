package com.softserve.edu.delivery.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.delivery.View;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 *
 * serves as a transport object between persistence and service layers
 */

public class FeedbackDTO {

    public FeedbackDTO() {
    }

    @JsonView (View.Feedback.class)
    private Long feedbackId;
    private Order order;
    @JsonView (View.Feedback.class)
    private String text;
    private User user;
    @JsonView (View.Feedback.class)
    private Integer rate;
    @JsonView (View.Feedback.class)
    private Boolean approved;
    @JsonView (View.Feedback.class)
    private Long orderId;
    @JsonView (View.Feedback.class)
    private String userName;
    @JsonView (View.Feedback.class)
    private String transporterName;
    private Timestamp createdOn;
    @JsonView (View.Feedback.class)
    private String stringCreatedOn;

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
        this.orderId = order.getId();
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
        this.userName = user.getFirstName() + " " + user.getLastName();

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
        DateFormat df = DateFormat.getDateTimeInstance();
        this.stringCreatedOn = df.format(createdOn);
    }

    public String getStringCreatedOn() {
        return stringCreatedOn;
    }

    public void setStringCreatedOn(String stringCreatedOn) {
        this.stringCreatedOn = stringCreatedOn;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "feedbackId=" + feedbackId +
                '}';
    }
}
