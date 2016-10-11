package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.City;
/**
 * Created by Natalia on 18.09.2016.
 */


public class CityDto {
    private Long cityId;
    private String name;
    private RegionDto region;

    public CityDto(){}

    public CityDto(Long cityId, String name, RegionDto region) {
        this.cityId = cityId;
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

    public RegionDto getRegion() {
        return region;
    }

    public void setRegion(RegionDto region) {
        this.region = region;
    }

    public static CityDto convertEntity(City city){
        return new CityDto(city.getCityId(), city.getCityName(), RegionDto.convertEntity(city.getRegion()));
    }
    public String toString() {
        return "name = " + name + " region = " + region;
    }
}
