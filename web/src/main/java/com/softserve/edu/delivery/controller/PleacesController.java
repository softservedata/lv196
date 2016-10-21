package com.softserve.edu.delivery.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.PlaceDTO;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.service.LocationService;
import com.softserve.edu.delivery.service.TransporterService;
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

    @RequestMapping(path ="/track", method = RequestMethod.GET)
    public List<PlaceDTO> getTracking() {
        List<PlaceDTO> list = teTransporterService.getAllPleaces();
        return list;
    }



}
