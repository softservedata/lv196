package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.RouteDTO;
import com.softserve.edu.delivery.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych !!!
 *
 * */
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private final OrderDao orderDao;
    private final RouteCityDao routeCityDao;
    private final OfferDao offerDao;

    @Autowired
    public RouteServiceImpl(OrderDao orderDao, RouteCityDao routeCityDao, OfferDao offerDao) {
        this.orderDao = orderDao;
        this.routeCityDao = routeCityDao;
        this.offerDao = offerDao;
    }

    /**This method is responsible to get information(tracking) about user's order
     * @param id - order number (tracking page)
     * @throws IllegalArgumentException if param ref null or id doesn't exist in database
     * @throws RuntimeException if database errors occur
     * */
    @Override
    public RouteDTO getRouteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id shouldn't be null!");
        }
        Order order;
        RouteCities lastTrack;  // Last tracked city
        List<RouteCities> trackingList; // All tracked cities
        Offer approvedOffer;
        EntityTransaction tx = null;
        try {
            EntityManager em = /*Jpa.getEntityManager(); Entity manager in service? */ null;
            tx = em.getTransaction();
            tx.begin();
            order = orderDao.findOne(id).get(); // find order by given id
            lastTrack = routeCityDao.getRouteCityWhenLastVisitedByOrder(order);
            trackingList = routeCityDao.getRouteCitiesByOrder(order); //get all tracked cities
            approvedOffer = offerDao.getApprovedOfferByOrder(order);
        }catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
        if (order == null) {
            tx.commit();
            throw new IllegalArgumentException("id doesn't exist");
        }
        RouteDTO result = createRouteDTO(order, lastTrack, trackingList, approvedOffer);
        tx.commit();
        return result;
    }


    //<----------------------------------------Private--------------------------------------->

    //Retrieve all parameters
    private static RouteDTO createRouteDTO(Order order, RouteCities lastTrack, List<RouteCities> trackingList, Offer approvedOf) {
        City lastCity = lastTrack.getCity(); // get last visited city
        Timestamp lastTime = lastTrack.getVisitDate(); // get time when visited last city
        Timestamp expectedTime = order.getArrivalDate(); //get expected arrival time
        List<City> visitedCities = retrieveAllVisitedCities(trackingList); // get all visited places
        BigDecimal height = order.getHeight(); //Get baggage parameters
        BigDecimal width = order.getWidth();
        BigDecimal length = order.getLength();
        BigDecimal weight = order.getWeight();
        User owner = order.getCustomer(); //get customer
        User transporter = approvedOf.getCar().getDriver(); // get transporter
        OrderStatus status = order.getOrderStatus();
        //Return result
        return new RouteDTO(lastCity, expectedTime, lastTime, visitedCities, height, width, length, weight, owner, transporter, status);
    }

    private static List<City> retrieveAllVisitedCities(List<RouteCities> trackingList) {
        if (trackingList == null || trackingList.isEmpty()) {
            return null;
        }
        List<City> result = new ArrayList<>();
        for (RouteCities tc : trackingList) {
            result.add(tc.getCity());
        }
        return result;
    }
}
