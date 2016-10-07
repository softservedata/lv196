package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.City;
/**
 * Created by Natalia on 18.09.2016.
 */


public class CityDto {
    private Long cityId;
    private String name;
    private Region region;

    public CityDto(){}

    public CityDto(Long cityId, String name, Region region) {
        this.cityId = cityId;
        this.name = name;
        this.region = region;
    }
    public CityDto(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public static CityDto convertEntity(City city){
        return new CityDto(city.getCityId(), city.getCityName(), city.getRegion());
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", region=" + region.toString() +
                '}';
    }
}
