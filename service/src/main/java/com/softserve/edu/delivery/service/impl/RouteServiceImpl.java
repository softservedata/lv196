package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.utils.Jpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych
 *
 * */
@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private EntityManager entityManager;

    private OrderDao orderDao;
    private RouteCityDao routeCityDao;
    private final OfferDao offerDao;

    @Autowired
    public RouteServiceImpl(OrderDao orderDao, RouteCityDao routeCityDao, OfferDao offerDao) {
        this.orderDao = orderDao;
        this.routeCityDao = routeCityDao;
        this.offerDao = offerDao;
    }

    /**This method is responsible to get information(tracking) about user's order
     * @param id - order number (tracking page)
     * */
    @Override
    public OrderRouteDto getOrderRouteById(OrderIdDto id) {
        Order order = orderDao.findOne(id.getOrderId()).get(); // find order by given id
        RouteCities lastTrack = routeCityDao.getRouteCityWhenLastVisitedByOrder(order);
        List<RouteCities> trackingList = routeCityDao.getRouteCitiesByOrder(order); //get all tracked cities
        Offer approvedOffer = offerDao.getApprovedOfferByOrder(order);
        OrderRouteDto result = createRouteDTO(order, lastTrack, trackingList, approvedOffer);
        return result;
    }


    //<----------------------------------------Private--------------------------------------->

    //Retrieve all parameters
    private static OrderRouteDto createRouteDTO(Order order, RouteCities lastTrack, List<RouteCities> trackingList, Offer approvedOf) {
        String cityFrom = order.getCityFrom().getCityName();
        String cityTo = order.getCityTo().getCityName();
        String lastLocation = lastTrack.getCity().getCityName();
        LocalDate expArrivalDate = order.getArrivalDate().toLocalDateTime().toLocalDate();
        LocalDate lastTimeVisited = lastTrack.getVisitDate().toLocalDateTime().toLocalDate();
        BigDecimal height = order.getHeight(); //Get baggage parameters
        BigDecimal width = order.getWidth();
        BigDecimal length = order.getLength();
        BigDecimal weight = order.getWeight();
        String customerName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
        String transporterName = approvedOf.getCar().getDriver().getFirstName() + " "
                + approvedOf.getCar().getDriver().getLastName();
        String receiverName = customerName;
        String orderStatus = order.getOrderStatus().getName();

        return new OrderRouteDto(cityFrom, cityTo, lastLocation, expArrivalDate, lastTimeVisited, height,
                width, length, weight, customerName, transporterName, receiverName, orderStatus);
    }
}