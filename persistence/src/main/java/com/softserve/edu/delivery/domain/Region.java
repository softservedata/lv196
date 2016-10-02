package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", state=" + state.toString() +
                '}';
    }
}
