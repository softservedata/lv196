package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.repository.CityRepository;
import com.softserve.edu.delivery.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final RouteCityDao routeCityDao;

    @Autowired
    public LocationServiceImpl(CityRepository cityRepository, RouteCityDao routeCityDao) {
        this.cityRepository = cityRepository;
        this.routeCityDao = routeCityDao;
    }

    @Override
    public List<LocationDto> findCitiesByName(String name) {
        return cityRepository
                .findTop10ByCityNameStartingWithIgnoreCaseOrderByCityName(name)
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
    public static RouteCities convertToEntity(PleaceDto pleaceDto) {
        return new RouteCities(pleaceDto.getCity(), Timestamp.valueOf(pleaceDto.getDate()));
    }

    @Override
    public void savePleace(PleaceDto pleaceDto){
        RouteCities routeCities = convertToEntity(pleaceDto);
        System.out.println("routeCities : "+routeCities.toString());
        routeCityDao.save(routeCities);
    }

}
