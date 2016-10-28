package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.dto.RoutesDto;
import com.softserve.edu.delivery.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping
public class PleacesController {

    @Autowired
    private TransporterService teTransporterService;

    @PreAuthorize(DRIVER)
    @RequestMapping(path ="/track/{id}", method = RequestMethod.GET)
    public List<PlaceDto> getTracking(@PathVariable Long id) {
        return teTransporterService.getAllPleacesByOrderId(id);
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path ="/routes/{size}/{page}", method = RequestMethod.GET)
    public List<RoutesDto> getAllRoutes(@PathVariable int size, @PathVariable int page){
        Pageable pageable = new PageRequest(page, size);
        return teTransporterService.getAllOrdersInProgress(pageable);
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path ="/count", method = RequestMethod.GET)
    public Long getCountPages(){
        return  teTransporterService.getCount();
    }
}
