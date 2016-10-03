package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "in-progress", method = RequestMethod.GET)
    List<OrderForListDto> inProgress() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findAllInProgressOrders(email);
    }

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderForListDto> open() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findAllOpenOrders(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    void addOrder(@RequestBody OrderForAddDto dto) {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        orderService.addOrder(dto, email);
    }

    @RequestMapping(path = "feedback", method = RequestMethod.POST)
    void addFeedback(@RequestBody FeedbackDTO dto) {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        orderService.addFeedback(dto, email);
    }

    @RequestMapping(path = "change", method = RequestMethod.PUT)
    void changeOfferStatus(@RequestBody OfferDto offerDto) {
        orderService.changeStatus(offerDto.getOfferId(),offerDto.isApproved());
    }

    /*--------------------IvanSynyshyn----------------------------*/
    @RequestMapping(path = "filtered_by_city_from", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityFrom() {
        String name = "Lviv";
        return orderService.getOrdersByCityFrom(name);
    }

    @RequestMapping(path = "filtered_by_city_to", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityTo() {
        String name = "Dnipro";
        return orderService.getOrdersByCityTo(name);
    }

    @RequestMapping(path = "filtered_by_weight", method = RequestMethod.GET)
    List<OrderForListDto> filteredByWeight() {
        BigDecimal weight = BigDecimal.valueOf(1000.0);
        return orderService.getOrdersByWeight(weight);
    }

    @RequestMapping(path = "filtered_by_arrival_date", method = RequestMethod.GET)
    List<OrderForListDto> filteredByArriwalDate() {
        Date milis = null;
        Timestamp date = new Timestamp(milis.getTime());
        return orderService.getOrdersByArriwalDate(date);
    }

    @RequestMapping(method = RequestMethod.PUT)
    List<Offer> addOffer(@RequestBody OrderForListDto order) {
        return orderService.addOffer(order.getId(), new Offer());
    }


}
