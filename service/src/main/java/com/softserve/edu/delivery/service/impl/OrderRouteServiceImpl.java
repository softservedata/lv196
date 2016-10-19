package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.exception.OrderNotFoundException;
import com.softserve.edu.delivery.service.OrderRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Petro Shtenovych
 *
 * */
@Service("routeService")
@Transactional
public class OrderRouteServiceImpl /*implements OrderRouteService */{

    private final OrderDao orderDao;
    private final RouteCityDao routeCityDao;
    private final OfferDao offerDao;

    @Autowired
    public OrderRouteServiceImpl(OrderDao orderDao, RouteCityDao routeCityDao, OfferDao offerDao) {
        this.orderDao = orderDao;
        this.routeCityDao = routeCityDao;
        this.offerDao = offerDao;
    }

   /* @Override
    public boolean exists(Long id) {
        return (orderDao.findOne(id).isPresent());
    }

    *//**This method is responsible to get information(tracking) about user's order
     * @param id - order number (tracking page)
     * @throws OrderNotFoundException if order isn't exist
     * */
  /*  @Override
    public OrderRouteDto getOrderRouteById(OrderIdDto id) {
        if ( ! this.exists(id.getOrderId())) {
            throw new OrderNotFoundException(id.getOrderId());
        }
        Order order = orderDao.findOne(id.getOrderId()).get();
        RouteCities lastTrack = routeCityDao.getRouteCityWhenLastVisitedByOrder(order);
        List<RouteCities> trackingList = routeCityDao.getRouteCitiesByOrder(order); //get all tracked cities
        Offer approvedOffer = offerDao.getApprovedOfferByOrder(order);
        return createRouteDTO(order, lastTrack, trackingList, approvedOffer);
    }*/


    //<----------------------------------------Private--------------------------------------->

    //Retrieve all parameters
    /*private static OrderRouteDto createRouteDTO(Order order, RouteCities lastTrack, List<RouteCities> trackingList, Offer approvedOf) {
        String cityFrom = isNull(order.getCityFrom()) ? null : order.getCityFrom().getCityName();
        String cityTo = isNull(order.getCityTo()) ? null : order.getCityTo().getCityName();
        String lastLocation = isNull(lastTrack) ? null : lastTrack.getCity().getCityName();
        LocalDate expArrivalDate = isNull(order.getArrivalDate()) ? null : order.getArrivalDate().toLocalDateTime().toLocalDate();
        LocalDate lastTimeVisited = isNull(lastTrack) ? null : lastTrack.getVisitDate().toLocalDateTime().toLocalDate();
        BigDecimal height = order.getHeight();
        BigDecimal width = order.getWidth();
        BigDecimal length = order.getLength();
        BigDecimal weight = order.getWeight();
        String customerName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
        String transporterName = isNull(approvedOf.getCar()) ? null : approvedOf.getCar().getDriver().getFirstName() + " "
                + approvedOf.getCar().getDriver().getLastName();
        String receiverName = customerName;
        String orderStatus = order.getOrderStatus().getName();

        return new OrderRouteDto(cityFrom, cityTo, lastLocation, expArrivalDate, lastTimeVisited, height,
                width, length, weight, customerName, transporterName, receiverName, orderStatus);
    }*/

    private static boolean isNull(Object obj) {
        return obj == null;
    }
}