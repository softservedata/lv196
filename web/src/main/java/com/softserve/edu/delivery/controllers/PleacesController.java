package com.softserve.edu.delivery.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.service.TransporterService;
import com.softserve.edu.delivery.service.impl.RouteServiceImpl;
import com.softserve.edu.delivery.service.impl.TransporterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path ="/tracking")
public class PleacesController {
    @Autowired
    private TransporterService teTransporterService;
    private RouteService routeService;

    @RequestMapping(path ="/state")
    public List<StateDto> getAllStates() {

        return teTransporterService.getAllState();
    }

    @RequestMapping(path ="/region", method = RequestMethod.POST)
    public List<RegionDto> getRegionByState( @RequestBody String state) {
        System.out.println(state);
        List<RegionDto> regionDto =  teTransporterService.getRegionByState(state);
        System.out.println(regionDto);
        return regionDto;
    }

    @RequestMapping(path ="/city",  method = RequestMethod.POST)
    public List<CityDto> getCityByRegion(@RequestBody String region) {
        System.out.println(region);
        List<CityDto> cityDto = teTransporterService.getCityByRegion(region);
        System.out.println(cityDto);
        return cityDto;
    }
    @RequestMapping(path ="/track")
    public Map<Date, CityDto> getTracking() {
        return null;//teTransporterService.getCityByRegion(region);
    }

    @RequestMapping(path ="/add",  method = RequestMethod.POST)
    public void addPleace(@RequestBody String city) {
        System.out.println(city);
        ObjectMapper objectMapper = new ObjectMapper();
        CityDto cityDto = new CityDto();
        try {
            cityDto = objectMapper.readValue(city, CityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String curStringDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(System.currentTimeMillis());
        System.out.println("String: " + curStringDate+" "+cityDto.toString());*/
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PleaceDto pleaceDto = new PleaceDto(cityDto, timestamp);
        System.out.println(pleaceDto.toString());
        routeService.savePleace(pleaceDto);

    }


}
