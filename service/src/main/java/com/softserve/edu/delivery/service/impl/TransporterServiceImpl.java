package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;
import com.softserve.edu.delivery.service.TransporterService;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import  com.softserve.edu.delivery.dao.CityDao;
import  com.softserve.edu.delivery.dao.RegionDao;
import  com.softserve.edu.delivery.dao.StateDao;
import com.softserve.edu.delivery.utils.TransactionManager;


public class TransporterServiceImpl implements TransporterService {
    private final CityDao cityDao;
    private final RegionDao regionDao;
    private final StateDao stateDao;

    public TransporterServiceImpl(CityDao cityDao,RegionDao regionDao, StateDao stateDao){
        this.cityDao = cityDao;
        this.regionDao = regionDao;
        this.stateDao = stateDao;
    }
    public List<StateDto> getAllState() {
        return TransactionManager.withTransaction(() ->
                stateDao.getAllState()
                        .stream()
                        .map(entity -> StateDto.convertEntity(entity))
                        .collect(Collectors.toList())
        );
    }
    public List<RegionDto> getRegionByState(String state){
        return TransactionManager.withTransaction(() ->
                regionDao.getRegionByState(state)
                        .stream()
                        .map(entity -> RegionDto.convertEntity(entity))
                        .collect(Collectors.toList())
        );

    }
    public List<CityDto> getCityByRegion(String region){
        return TransactionManager.withTransaction(() ->
                cityDao.getCityByRegion(region)
                        .stream()
                        .map(entity -> CityDto.convertEntity(entity))
                        .collect(Collectors.toList())
        );

    }


}
