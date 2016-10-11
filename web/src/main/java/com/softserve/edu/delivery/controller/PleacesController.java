package com.softserve.edu.delivery.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.delivery.domain.Order;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
        Order order = new Order().setId(2L);
        List<PleaceDto> list = teTransporterService.getAllPleaces();
        for (PleaceDto p : list){
            System.out.println(p.toString());
        }
        //System.out.println(list.get(0).getCity().getRegion().getState().toString());
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
        Order order = new Order().setId(2L);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PleaceDto pleaceDto = new PleaceDto(cityDto, timestamp.toString());
        routeService.savePleace(pleaceDto);
    }


}
