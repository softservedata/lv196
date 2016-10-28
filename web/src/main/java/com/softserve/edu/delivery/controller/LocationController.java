package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    Logger logger = LoggerFactory.getLogger(LocationController.class.getName());

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(method = RequestMethod.GET)
    List<LocationDto> locations(@RequestParam(value = "city") String city) {
        return StringUtils.isEmpty(city) ? Collections.emptyList() :
                locationService.findCitiesByName(city);
    }
}
