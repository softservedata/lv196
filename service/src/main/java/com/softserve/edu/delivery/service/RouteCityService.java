package com.softserve.edu.delivery.service;

import java.util.List;

import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.RouteCityDTO;

public interface RouteCityService {

    List<RouteCityDTO> getRouteCitiesByOrderId(Long orderId);
        void saveRouteCity(RouteCities routeCities);
}
