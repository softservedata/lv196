package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;

public class LocationDto {
    private Long cityId;
    private String cityName;

    public LocationDto() {}

    public static LocationDto of(City city) {
        return new LocationDto()
                .setCityId(city.getCityId())
                .setCityName(city.getCityName());
    }

    public Long getCityId() {
        return cityId;
    }

    public LocationDto setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public LocationDto setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }
}
