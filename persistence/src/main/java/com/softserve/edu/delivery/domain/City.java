package com.softserve.edu.delivery.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    private Double latitude;
    private Double longitude;


    public City() {
    }

    public City(String cityName, Region region) {
        this.cityName = cityName;
        this.region = region;
    }

    public City(Long cityId, String cityName, Region region) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.region = region;
    }

    public Long getCityId() {
        return cityId;
    }

    public City setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public City setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public City setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public City setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public City setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
    @Override
    public String toString() {
        return "City [id = " + cityId + ", City = " + cityName + ", Region = " + region + "]";
    }
}
