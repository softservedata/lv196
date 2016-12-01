package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class EmailVerificationToken {

    //how much time token will allow
    private static final long EXPIRATION_INTERVAL = 1000 * 60 * 60 * 24; // 24 hours by default

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Timestamp expiredDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "email", nullable = false)
    private User user;


    public EmailVerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiredDate = new Timestamp(System.currentTimeMillis() + EXPIRATION_INTERVAL);
    }

    protected EmailVerificationToken() {}

    public EmailVerificationToken setId(Long id) {
        this.id = id;
        return this;
    }

    public EmailVerificationToken setToken(String token) {
        this.token = token;
        return this;
    }

    public EmailVerificationToken setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public EmailVerificationToken setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public User getUser() {
        return user;
    }
}
