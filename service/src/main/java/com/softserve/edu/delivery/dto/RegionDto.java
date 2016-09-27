package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;

/**
 * Created by Natalia on 18.09.2016.
 */

public class RegionDto {
    private Long regionId;
    private String name;
    private State state;

    public RegionDto(){}

    public RegionDto(Long regionId, String name, State state) {
        this.regionId = regionId;
        this.name = name;
        this.state = state;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static RegionDto convertEntity(Region region){
        return new RegionDto(region.getRegionId(), region.getRegionName(), region.getState());
    }
}
