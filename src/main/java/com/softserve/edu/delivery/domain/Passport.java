package com.softserve.edu.delivery.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PASSPORTS")
@Access(AccessType.PROPERTY)
@Proxy(lazy = false)
public class Passport implements Serializable{

    @Id
    @Column(name = "passport_id")
    private Integer passportId;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "first_photo_url")
    private String firstPhotoUrl;

    @Column(name = "second_photo_url")
    private String secondPhotoUrl;

    @Column(name = "approved")
    private Boolean approved;

    public Passport(String series, String number, String firstPhotoUrl, String secondPhotoUrl, Boolean approved) {
        this.series = series;
        this.number = number;
        this.firstPhotoUrl = firstPhotoUrl;
        this.secondPhotoUrl = secondPhotoUrl;
        this.approved = approved;
    }

    public Passport() {
    }

    public Integer getPassportId() {
        return passportId;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstPhotoUrl() {
        return firstPhotoUrl;
    }

    public void setFirstPhotoUrl(String firstPhotoUrl) {
        this.firstPhotoUrl = firstPhotoUrl;
    }

    public String getSecondPhotoUrl() {
        return secondPhotoUrl;
    }

    public void setSecondPhotoUrl(String secondPhotoUrl) {
        this.secondPhotoUrl = secondPhotoUrl;
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

        Passport passport = (Passport) o;

        if (series != null ? !series.equals(passport.series) : passport.series != null) return false;
        return number != null ? number.equals(passport.number) : passport.number == null;

    }

    @Override
    public int hashCode() {
        int result = series != null ? series.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passportId=" + passportId +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}