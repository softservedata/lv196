package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;

import java.util.Objects;
import java.util.Optional;

public class LocationDto {
    private Long cityId;
    private String cityName;
    private String regionName;
    private String stateName;
    private Double latitude;
    private Double longitude;

    public LocationDto() {}

    public static LocationDto of(City city) {
        Objects.requireNonNull(city, "No city found");
        Optional<Region> regionOptional = Optional.ofNullable(city.getRegion());

        String regionName = regionOptional.map(Region::getRegionName).orElse(null);

        String stateName = regionOptional
                .map(Region::getState)
                .map(State::getStateName)
                .orElse(null);

        return new LocationDto()
                .setCityId(city.getCityId())
                .setCityName(city.getCityName())
                .setRegionName(regionName)
                .setStateName(stateName)
                .setLatitude(city.getLatitude())
                .setLongitude(city.getLongitude());
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

    public String getRegionName() {
        return regionName;
    }

    public LocationDto setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public LocationDto setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public LocationDto setLatitude(Double double1) {
        this.latitude = double1;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocationDto setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
