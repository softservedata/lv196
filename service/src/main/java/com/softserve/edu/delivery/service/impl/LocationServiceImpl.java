package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.domain.State;
import com.softserve.edu.delivery.dto.*;
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

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RouteCityDao routeCityDao;
    @Autowired
    private RegionDao regionDao;

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

    @Override
    public void savePleace(PleaceDto pleaceDto){
        RouteCities routeCities = convertToEntity(pleaceDto);
        System.out.println("routeCities : "+routeCities.toString());
        routeCityDao.save(routeCities);
    }

    public  RouteCities convertToEntity(PleaceDto pleaceDto) {
        CityDto cityDto = pleaceDto.getCity();
        return new RouteCities(convertToEntity(cityDto), Timestamp.valueOf(pleaceDto.getDate()));
    }

    public static Region convertToEntity(RegionDto regionDto) {
        return new Region(regionDto.getName(), convertToEntity(regionDto.getState()));
    }

    public static State convertToEntity(StateDto stateDto) {
        return new State(stateDto.getName());
    }


    public City convertToEntity(CityDto cityDto) {

        return new City(cityDto.getCityId(), cityDto.getName(), convertToEntity(cityDto.getRegion()));
    }

}
