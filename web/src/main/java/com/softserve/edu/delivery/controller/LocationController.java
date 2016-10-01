package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(path = "locations", method = RequestMethod.GET)
    List<LocationDto> locations(@RequestParam(value = "name", required = false) String name) {
        return StringUtils.isEmpty(name) ? locationService.findAllCities() :
                locationService.findCitiesByName(name);
    }
}
