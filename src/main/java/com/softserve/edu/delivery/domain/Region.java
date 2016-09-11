package com.softserve.edu.delivery.domain;

import javax.persistence.*;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */
@Entity
@Table(name = "REGION")
public class Region {

    public Region() {
    }

    private Long regionId;

    private String regionName;

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
}
