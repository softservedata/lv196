package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */
@Entity
@Table(name = "REGIONS")
public class Region {

    public Region() {
    }

    private Long regionId;
    private String regionName;
    private State state;
    private List<City> cities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Column(name = "region_name")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @ManyToOne
    @JoinColumn(name = "state_id")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @OneToMany
    @JoinColumn(name = "city_id")
    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        cities.add(city);
    }
}
