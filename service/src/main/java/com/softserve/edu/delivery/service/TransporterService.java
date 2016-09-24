package com.softserve.edu.delivery.service;

import java.util.List;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;

public interface TransporterService {
    List<StateDto> getAllState();
    List<RegionDto> getRegionByState(String state);
    List<CityDto> getCityByRegion(String region);
}
