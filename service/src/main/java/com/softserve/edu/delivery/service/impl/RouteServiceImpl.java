package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.RouteDTO;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
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
    private OfferDao offerDao;

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
        List<RouteCities> trackingList;
        List<Offer> offerList;
        EntityTransaction tx = null;
        try {
            EntityManager em = Jpa.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            order = orderDao.findOne(id).get(); // find order by given id
            trackingList = routeCityDao.getRouteCitiesByOrder(order); //get all tracked cities
            offerList = offerDao.getAllOffersByOrder(order);
        }catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
        if (order == null) {
            tx.commit();
            throw new IllegalArgumentException("id doesn't exist");
        }
        RouteDTO result = getRouteDTO(order, trackingList, offerList);
        tx.commit();
        return result;
    }


    //<----------------------------------------Private--------------------------------------->

    //Retrieve all parameters
    private static RouteDTO getRouteDTO(Order order, List<RouteCities> trackingList, List<Offer> offerList) {
        City lastCity = calcLastVisitedCity(trackingList); // get last visited city
        Timestamp lastTime = getLastTime(trackingList); // get time when visited last city
        Timestamp expectedTime = order.getArrivalDate(); //get expected arrival time
        List<City> visitedCities = getAllVisitedCities(trackingList); // get all visited places
        BigDecimal height = order.getHeight(); //Get baggage parameters
        BigDecimal width = order.getWidth();
        BigDecimal length = order.getLength();
        BigDecimal weight = order.getWeight();
        User owner = order.getCustomer(); //get customer
        User transporter = getTransporterByOffers(offerList); // get transporter from offer which was improved
        OrderStatus status = order.getOrderStatus();
        //Return result
        return new RouteDTO(lastCity, expectedTime, lastTime, visitedCities, height, width, length, weight, owner, transporter, status);
    }

    private static City calcLastVisitedCity(List<RouteCities> trackingList) {
        if (trackingList == null || trackingList.isEmpty()) {
            return null;
        }
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
        if (trackingList == null || trackingList.isEmpty()) {
            return null;
        }
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
        if (trackingList == null || trackingList.isEmpty()) {
            return null;
        }
        List<City> result = new ArrayList<>();
        for (RouteCities tc : trackingList) {
            result.add(tc.getCity());
        }
        return result;
    }

    private static User getTransporterByOffers(List<Offer> offerList) {
        if (offerList == null || offerList.isEmpty()) {
            return null;
        }
        for (Offer offer : offerList) {
            if (offer.getApproved()) {
                return offer.getCar().getUser();
            }
        }
        return null;
    }
}
