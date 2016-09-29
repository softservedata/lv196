package com.softserve.edu.delivery.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path ="/tracking")
public class PleacesController {
    @Autowired
    private TransporterService teTransporterService;

    @RequestMapping(path ="/state")//method default
    public List<StateDto> getAllStates() {

        return teTransporterService.getAllState();
    }

    @RequestMapping(value ="/region{state}", method = RequestMethod.GET)
    public List<RegionDto> getRegionByState( @PathVariable String state) {
        return teTransporterService.getRegionByState(state);
    }

    @RequestMapping(path ="/city")
    public List<CityDto> getCityByRegion(String region) {
        return teTransporterService.getCityByRegion(region);
    }
    @RequestMapping(path ="/track")
    public Map<Date, CityDto> getTracking() {
        return null;//teTransporterService.getCityByRegion(region);
    }


}
