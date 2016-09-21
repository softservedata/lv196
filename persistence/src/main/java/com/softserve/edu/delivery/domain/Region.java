package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Rudnytskyi, 11.09.2016.
 */
@Entity
@Table(name = "REGIONS")
public class Region {

    public Region() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;
    @Column(name = "region_name")
    private String regionName;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @OneToMany(mappedBy = "region",
                    cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<City> cities;


    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


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
