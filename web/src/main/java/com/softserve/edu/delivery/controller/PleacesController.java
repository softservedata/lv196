package com.softserve.edu.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.service.LocationService;
import com.softserve.edu.delivery.service.TransporterService;
import com.softserve.edu.delivery.service.impl.TransporterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class PleacesController {
    @Autowired
    private TransporterService teTransporterService;
    @Autowired
    private LocationService routeService;

    @RequestMapping(path ="/state")
    public List<StateDto> getAllStates() {

        return teTransporterService.getAllState();
    }

    @RequestMapping(path ="/region", method = RequestMethod.POST)
    public List<RegionDto> getRegionByState(@RequestBody String state) {
        List<RegionDto> regionDto =  teTransporterService.getRegionByState(state);
        return regionDto;
    }

    @RequestMapping(path ="/city",  method = RequestMethod.POST)
    public List<CityDto> getCityByRegion(@RequestBody String region) {
        List<CityDto> cityDto = teTransporterService.getCityByRegion(region);
        return cityDto;
    }
    @RequestMapping(path ="/track", method = RequestMethod.GET)
    public List<PleaceDto> getTracking() {
        List<PleaceDto> list = teTransporterService.getAllPleaces();
        return list;
    }

    @RequestMapping(path ="/add",  method = RequestMethod.POST)
    public void addPleace(@RequestBody String city) {
        ObjectMapper objectMapper = new ObjectMapper();
        CityDto cityDto = new CityDto();
        try {
            cityDto = objectMapper.readValue(city, CityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PleaceDto pleaceDto = new PleaceDto(TransporterServiceImpl.convertToEntity(cityDto), timestamp.toString());
        routeService.savePleace(pleaceDto);
    }


}
