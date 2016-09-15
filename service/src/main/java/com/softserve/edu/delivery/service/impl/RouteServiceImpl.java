package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.RouteDTO;
import com.softserve.edu.delivery.service.RouteService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych
 *
 * */

public class RouteServiceImpl implements RouteService {

    private OrderDao orderDao;
    private RouteCityDao routeCityDao;

    public RouteServiceImpl(OrderDao orderDao, RouteCityDao routeCityDao) {
        this.orderDao = orderDao;
        this.routeCityDao = routeCityDao;
    }

    //This method is responsible to get information(tracking) about user's order
    @Override
    public RouteDTO getRouteById(Long id) {
        Order order = orderDao.findOne(id).get(); // find order by given id

        //Retrieve all parameters
        List<RouteCities> trackingList = routeCityDao.getRouteCitiesByOrder(order); //get all tracked cities
        City lastLocation = calcLastVisitedCity(trackingList); // get last visited city
        Timestamp lastTimeVisited = getLastTime(trackingList); // get time when visited last city
        Timestamp expectedArrivalTime = order.getArrivalDate(); //get expected arrival time
        List<City> visitedCities = getAllVisitedCities(trackingList); // get all visited places
        Double h = order.getHeight(); //Get baggage parameters
        Double w = order.getWidth();
        Double l = order.getLength();
        Double wei = order.getWeight();
        User owner = order.getUser();
        User transporter = null; //todo: realize it later
        OrderStatus status = order.getOrderStatus();
        //Return result
        return new RouteDTO(lastLocation, expectedArrivalTime, lastTimeVisited, visitedCities, h, w, l, wei, owner, transporter, status);
    }

    //<----------------Private---------------->

    private static City calcLastVisitedCity(List<RouteCities> trackingList) {
        City result = null;
        Long temp = 0L;
        for (RouteCities rc : trackingList) {
            if (rc.getVisitDate().getTime() > temp) {
                temp = rc.getVisitDate().getTime();
                result = rc.getCity();
            }
        }
        return result;
    }

    private static Timestamp getLastTime(List<RouteCities> trackingList) {
        Timestamp result = null;
        Long temp = 0L;
        for (RouteCities rc : trackingList) {
            if (rc.getVisitDate().getTime() > temp) {
                result = rc.getVisitDate();
            }
        }
        return result;
    }

    private static List<City> getAllVisitedCities(List<RouteCities> trackingList) {
        List<City> result = new ArrayList<>();
        for (RouteCities tc : trackingList) {
            result.add(tc.getCity());
        }
        return result;
    }
}
