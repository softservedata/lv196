package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;
import com.softserve.edu.delivery.service.TransporterService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.dao.StateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransporterServiceImpl implements TransporterService {
    private final CityDao cityDao;
    private final RegionDao regionDao;
    private final StateDao stateDao;

    @Autowired
    public TransporterServiceImpl(CityDao cityDao, RegionDao regionDao, StateDao stateDao) {
        this.cityDao = cityDao;
        this.regionDao = regionDao;
        this.stateDao = stateDao;
    }

    public List<StateDto> getAllState() {
        return stateDao.getAllState()
                .stream()
                .map(entity -> StateDto.convertEntity(entity))
                .collect(Collectors.toList());
    }

    public List<RegionDto> getRegionByState(String state) {
        return regionDao.getRegionByState(state)
                .stream()
                .map(entity -> RegionDto.convertEntity(entity))
                .collect(Collectors.toList());

    }

    public List<CityDto> getCityByRegion(String region) {
        return cityDao.getCityByRegion(region)
                .stream()
                .map(entity -> CityDto.convertEntity(entity))
                .collect(Collectors.toList());
    }


    public static State convertToEntity(StateDto stateDto) {
        return new State(stateDto.getName());
    }

    public static City convertToEntity(CityDto cityDto) {
        return new City(cityDto.getCityId(), cityDto.getName(), cityDto.getRegion());
    }

    public static Region convertToEntity(RegionDto regionDto) {
        return new Region(regionDto.getName(), regionDto.getState());
    }
}
