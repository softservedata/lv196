package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.dto.RouteCityDTO;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import com.softserve.edu.delivery.service.RouteCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RouteCityServiceImpl implements RouteCityService{

    @Autowired
    private RouteCitiesRepository routeCitiesRepository;
    
    @Override
    public List<RouteCityDTO> getRouteCitiesByOrderId(Long orderId) {
        List<RouteCities> routeCities = routeCitiesRepository.findRouteCitiesByOrderId(orderId);
        List<RouteCityDTO> routeCityDTOs = new ArrayList<>();
        for (RouteCities routeCity : routeCities) {
            RouteCityDTO routeCityDTO = new RouteCityDTO(PlaceDto.convertEntity(routeCity),
                    new Date(routeCity.getVisitDate().getTime()));
            
            routeCityDTOs.add(routeCityDTO);
        }
        return routeCityDTOs;
}

    @Override
    public void saveRouteCity(RouteCities routeCities) {
        routeCitiesRepository.save(routeCities);
    }
}
