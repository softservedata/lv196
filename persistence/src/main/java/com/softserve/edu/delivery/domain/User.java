package com.softserve.edu.delivery.domain;

import com.softserve.edu.delivery.domain.chat.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(unique = true)
    private String email;

    @Column(length = 60)
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks = new ArrayList<>();

    /* 10 is 1 star, 40 is 4 stars and so on */
    private Integer rate;
    private String photoUrl;
    private String passport;
    private Boolean approved;
    private Boolean blocked;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public List<Car> getCars() {
        return cars;
    }

    public User setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public User setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
        return this;
    }

    public Integer getRate() {
        return rate;
    }

    public User setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public User setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public Role getUserRole() {
        return userRole;
    }

    public User setUserRole(Role userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getPassport() {
        return passport;
    }

    public User setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public User setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public User setBlocked(Boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}