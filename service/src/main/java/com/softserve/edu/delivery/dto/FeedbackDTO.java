package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 *
 * serves as a transport object between persistence and service layers
 */
public class FeedbackDTO /*implements Serializable*/ {
    // private static final long serialVersionUID = 4710852270488569035L;

    public FeedbackDTO() {
    }

    private Long feedbackId;
    private Order order;
    private String text;
    private User user;
    private Integer rate;
    private Boolean approved;
    private Long orderId;
    private String userName;
    private String transporterName;

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
}
