package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "location")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<LocationDto> locations(@RequestParam(value = "city") String city) {
        return StringUtils.isEmpty(city) ? Collections.emptyList() :
                locationService.findCitiesByName(city);
    }
}
