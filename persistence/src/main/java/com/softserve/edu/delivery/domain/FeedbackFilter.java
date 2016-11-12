package com.softserve.edu.delivery.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class FeedbackFilter {
    private String text;
    private Integer rate;
    private String userName;
    private String transporterName;
    private Timestamp createdOn;
    private Boolean approved0;
    private Boolean approved1;
    private String sortBy;
    private String sortOrder;
    private int currentPage;
    private int itemsPerPage;

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

    public Boolean getApproved0() {
        return approved0;
    }

    public void setApproved0(Boolean approved0) {
        this.approved0 = approved0;
    }

    public Boolean getApproved1() {
        return approved1;
    }

    public void setApproved1(Boolean approved1) {
        this.approved1 = approved1;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
