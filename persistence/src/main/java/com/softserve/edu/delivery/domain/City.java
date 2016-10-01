package com.softserve.edu.delivery.domain;
/**
 * Author - Ivan Synyshyn
 */

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

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

    @Override
    public String toString() {
        return "City [id = " + cityId + ", City = " + cityName + ", Region = " + region + "]";
    }

}
