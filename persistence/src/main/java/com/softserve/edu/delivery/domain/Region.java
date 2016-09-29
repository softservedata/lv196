package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Region(String regionName, State state) {
        this.regionName = regionName;
        this.state = state;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;
        return region.getRegionName().equals(this.getRegionName());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getRegionName());
    }
}
