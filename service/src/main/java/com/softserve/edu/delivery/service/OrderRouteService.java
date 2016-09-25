package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;

/**
 * @author Petro Shtenovych
 * */

public interface OrderRouteService {

    boolean exists(Long id);

    OrderRouteDto getOrderRouteById(OrderIdDto orderId);

}
