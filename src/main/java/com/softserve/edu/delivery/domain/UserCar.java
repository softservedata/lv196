package com.softserve.edu.delivery.domain;

import javax.persistence.*;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */

public class UserCar {

    public UserCar() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usercar_id")
    private Long usercar_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Long getUsercar_id() {
        return usercar_id;
    }

    public void setUsercar_id(Long usercar_id) {
        this.usercar_id = usercar_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
