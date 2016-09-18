package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;
import com.softserve.edu.delivery.service.TransporterService;
import java.util.List;
import java.util.ArrayList;
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
    public List<StateDto> getAllState(){
        return TransactionManager.withTransaction(() ->
                copyStateToDto(stateDao.getAllState())
        );
    }
    public List<RegionDto> getRegionByState(String state){
        return TransactionManager.withTransaction(() ->
                copyRegionToDto(regionDao.getRegionByState(state))
        );
    }
    public List<CityDto> getCityByRegion(String region){
        return TransactionManager.withTransaction(() ->
                copyCityToDto(cityDao.getCityByRegion(region))
        );
    }
    private List<StateDto> copyStateToDto(List<State> list){
        List<StateDto> resultList = new ArrayList<StateDto>();
        StateDto dto = new StateDto();
        for(State state : list){
            dto.setName(state.getStateName());
            dto.setStateId(state.getStateId());
            resultList.add(dto);
        }
        return resultList;
    }
    private List<RegionDto> copyRegionToDto(List<Region> list){
        List<RegionDto> resultList = new ArrayList<RegionDto>();
        RegionDto dto = new RegionDto();
        for(Region region : list){
            dto.setName(region.getRegionName());
            dto.setRegionId(region.getRegionId());
            dto.setState(region.getState());
            resultList.add(dto);
        }
        return resultList;
    }

    private List<CityDto> copyCityToDto(List<City> list){
        List<CityDto> resultList = new ArrayList<>();
        CityDto dto = new CityDto();
        for(City city : list){
            dto.setName(city.getCityName());
            dto.setRegion(city.getRegion());
            dto.setCityId(city.getCityId());
            resultList.add(dto);
        }
        return resultList;
    }

}
