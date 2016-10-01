package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.repository.CityRepository;
import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;

    @Autowired
    public LocationServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<LocationDto> findCitiesByName(String name) {
        return cityRepository
                .findByCityNameContainingIgnoreCase(name)
                .stream()
                .map(LocationDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> findAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(LocationDto::of)
                .collect(Collectors.toList());
    }

}
