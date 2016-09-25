package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.exception.OrderNotFoundException;
import com.softserve.edu.delivery.service.OrderRouteService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service("fake")
public class OrderRouteServiceImplFake implements OrderRouteService {

    @Override
    public boolean exists(Long id) {
        return true;
    }

    OrderRouteDto orderRouteDto;

    @PostConstruct
    public void setUp() {
        this.orderRouteDto = new OrderRouteDto("Lviv", "Kyiv", "Lutsk", LocalDate.now().plusDays(2L), LocalDate.now(), new BigDecimal(3.5), new BigDecimal(5.0), new BigDecimal(3.5),
                new BigDecimal(45.0), "Petro Shtenovych", "Adam Lumberg", "Jonh Smith", "Active");
    }

    @Override
    public OrderRouteDto getOrderRouteById(OrderIdDto orderId) {
        if (orderId.getOrderId() == 13L) {
            throw new OrderNotFoundException(13L);
        }
        return this.orderRouteDto;
    }
}
