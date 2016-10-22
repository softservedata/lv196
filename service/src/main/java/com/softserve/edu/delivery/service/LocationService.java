package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> findCitiesByName(String name);
    List<LocationDto> findAllCities();

}
