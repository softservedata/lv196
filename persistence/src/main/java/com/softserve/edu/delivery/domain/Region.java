package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    private String regionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<City> cities;

    public Region() {
    }

    public Region(String regionName, State state) {
        this.regionName = regionName;
        this.state = state;
    }

    public Long getRegionId() {
        return regionId;
    }

    public Region setRegionId(Long regionId) {
        this.regionId = regionId;
        return this;
    }

    public String getRegionName() {
        return regionName;
    }

    public Region setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public Region setCities(List<City> cities) {
        this.cities = cities;
        return this;
    }

    public State getState() {
        return state;
    }

    public Region setState(State state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionId, region.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId);
    }
    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", state=" + state.toString() +
                '}';
    }
}
