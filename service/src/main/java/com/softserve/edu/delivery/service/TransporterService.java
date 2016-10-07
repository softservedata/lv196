package com.softserve.edu.delivery.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;

public interface TransporterService {
    /**
     * Finds all state and convert to dto.
     * @return list of state dto.
     */
    List<StateDto> getAllState();

    /**
     * Finds all region in state and convert to dto
     * @param state - name of state
     * @return list of region dto in state
     */
    List<RegionDto> getRegionByState(String state);

    /**
     * Finds all city in region and convert to dto
     * @param region - name of region
     * @return list of city dto in region
     */
    List<CityDto> getCityByRegion(String region);

    /**
     * Finds all pleaces wfrom tracking
     * @return map of visit time and city
     */
    List<PleaceDto> getAllPleaces();

 }
