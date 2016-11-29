package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.driver.Scheduler;
import com.softserve.edu.delivery.dto.OrderClosedDto;
import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.dto.RoutesDto;
import com.softserve.edu.delivery.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping
public class PleacesController {
    @Autowired
    private TransporterService teTransporterService;

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path ="/track/{id}", method = RequestMethod.GET)
    public RoutesDto getTracking(@PathVariable Long id) {
        return teTransporterService.getMyRoute(id);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path ="/points/{id}", method = RequestMethod.GET)
    public List<PlaceDto> getPoints(@PathVariable Long id) {
        return teTransporterService.getAllPointsByOrderIdWitoutDate(id);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path ="/getRoute/{id}", method = RequestMethod.GET)
    public OrderClosedDto getRoute(@PathVariable Long id) {
        return teTransporterService.findOneByIdAndOrderStatus(id);
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path ="/routes", method = RequestMethod.GET)
    public List<RoutesDto> getAllRoutes(){
        return teTransporterService.getAllOrdersInProgress();
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path ="/closedByDates", method = RequestMethod.GET)
    public List<OrderClosedDto> getAllRoutesClosedByDates(@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate){
        Timestamp min;
        if(fromDate.equals("null") || fromDate.equals("0")) {
            Calendar cal = Calendar.getInstance();
            Date today = cal.getTime();
            cal.add(Calendar.YEAR, -1);
            min = new Timestamp(cal.getTime().getTime());
        }
        else {
            min = new Timestamp(Long.valueOf(fromDate));
        }
        Timestamp max;
        if(toDate.equals("null") || toDate.equals("0")) {
            max = new Timestamp(new Date().getTime());
        }
        else {
            max = new Timestamp(Long.valueOf(toDate));
        }
        System.out.println("from: "+min+" to: "+max);
        return teTransporterService.getAllOrdersClosedByDates(min, max);
    }
    @PreAuthorize(DRIVER)
    @RequestMapping(path ="/setPoints", method = RequestMethod.PUT)
    public void setWaypoints(@RequestParam(value = "id") Long id, @RequestParam(value = "points") String [] points){
        List<Point> wayPoints = new ArrayList<>();
        for(String str : points){
            wayPoints.add(new Point(Double.parseDouble(str.split(" ")[0]),
                    Double.parseDouble(str.split(" ")[1])));
        }
        teTransporterService.setPoints(id, wayPoints);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path ="/start/{id}", method = RequestMethod.GET)
    public void startRoute(@PathVariable Long id) {
        teTransporterService.changeStatus(id, OrderStatus.IN_PROGRESS);
        List<PlaceDto> list = teTransporterService.getAllPointsByOrderIdWitoutDate(id);
        Scheduler.setFlag(true);
        Scheduler.setId(id);
        Scheduler.getPoints(list);
    }

}
