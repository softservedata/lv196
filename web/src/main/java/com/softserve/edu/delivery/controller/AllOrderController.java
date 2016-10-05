package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 05.10.2016.
 */

@RestController
@RequestMapping(path = "all-orders")
public class AllOrderController {
    private final OrderService orderService;

    @Autowired
    public AllOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderForListDto> open() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findOpenOrders(email);
    }

    @RequestMapping(path = "filtered-by-city-from", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityFrom(@RequestParam String city) {
        return orderService.getOrdersByCityFrom(city);
    }

    @RequestMapping(path = "filtered-by-city-to", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityTo(@RequestParam String city) {
        return orderService.getOrdersByCityTo(city);
    }

    @RequestMapping(path = "filtered-by-weight", method = RequestMethod.GET)
    List<OrderForListDto> filteredByWeight(@RequestParam String weight) {
        BigDecimal converted = BigDecimal.valueOf(Double.parseDouble(weight));
        return orderService.getOrdersByWeight(converted);
    }

    @RequestMapping(path = "filtered-by-arrival-date", method = RequestMethod.GET)
    List<OrderForListDto> filteredByArriwalDate(@RequestParam String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        Date parsed = null;
        try {
            parsed = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(parsed.getTime());
        return orderService.getOrdersByArriwalDate(timestamp);
    }

    @RequestMapping(path = "offer", method = RequestMethod.POST)
    List<OfferDto> addOffer(@RequestBody OrderForListDto order) {
        return orderService.addOffer(order.getId());
    }
}