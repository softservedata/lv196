package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.User;

import java.util.Date;

/**
 * Created by Ivan Synyshyn on 16.09.2016.
 */
public class OrdersForTranspDTO {

    private City cityFrom;
    private City cityTo;
    private User user;
    private Double weight;
    private Double volume;
    private Date startDate;
    private Date endDate;

    public OrdersForTranspDTO() {
    }

    public OrdersForTranspDTO(City cityFrom,
                              City cityTo,
                              User user,
                              Double weight,
                              Double volume,
                              Date startDate,
                              Date endDate) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.user = user;
        this.weight = weight;
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
