package com.softserve.edu.delivery.dto;

import java.sql.Timestamp;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 *
 * serves as a transport object between persistence and service layers
 */

public class FeedbackDTO {

    public FeedbackDTO() {
    }

    private Long feedbackId;
    private String text;
    private Integer rate;
    private Boolean approved;
    private Long orderId;
    private String userEmail;
    private String userName;
    private String transporterName;
    private String transporterEmail;
    private Timestamp createdOn;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTransporterEmail() {
        return transporterEmail;
    }

    public void setTransporterEmail(String transporterEmail) {
        this.transporterEmail = transporterEmail;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "feedbackId=" + feedbackId +
                '}';
    }
}
