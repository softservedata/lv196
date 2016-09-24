package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OrderRouteDto;

/**
 * @author Petro Shtenovych
 * */

public interface RouteService {

    OrderRouteDto getRouteById(Long id);

}
