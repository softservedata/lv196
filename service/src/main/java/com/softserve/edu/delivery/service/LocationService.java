package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.dto.PlaceDTO;

import java.util.List;

public interface LocationService {
    List<LocationDto> findCitiesByName(String name);
    List<LocationDto> findAllCities();

}
