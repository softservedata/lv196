package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.dto.RoutesDto;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import com.softserve.edu.delivery.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softserve.edu.delivery.domain.OrderStatus.IN_PROGRESS;

@Service
@Transactional
public class TransporterServiceImpl implements TransporterService {
    private Long count;
    private final RouteCitiesRepository routeCityRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TransporterServiceImpl(RouteCitiesRepository routeCityRepository, OrderRepository orderRepository) {
        this.routeCityRepository = routeCityRepository;
        this.orderRepository = orderRepository;
    }
    public List<PlaceDto> getAllPleaces() {
        return routeCityRepository.findAll()
                .stream()
                .map(entity -> PlaceDto.convertEntity(entity))
                .collect(Collectors.toList());
    }
    @Override
    public List<PlaceDto> getAllPleacesByOrderId(Long id) {
        return routeCityRepository.findByOrderId(id)
                .stream()
                .map(entity -> PlaceDto.convertEntity(entity))
                .collect(Collectors.toList());
    }
    @Override
    public List<RoutesDto> getAllOrdersInProgress(Pageable pageable){
        Page<Order> list= orderRepository.findAllByOrderStatus(IN_PROGRESS, pageable);
        count = list.getTotalElements();
        List<RoutesDto> resultList = new ArrayList<>();
        for(Order o : list) {
            Long id = o.getId();
            RouteCities routeCities = routeCityRepository.findCurrentLocation(id);
            resultList.add(new RoutesDto(
                    new Point(o.getLocationFrom().getLatitude(), o.getLocationFrom().getLongitude()),
                    new Point(o.getLocationTo().getLatitude(), o.getLocationTo().getLongitude()),
                    new PlaceDto(new Point(routeCities.getX(), routeCities.getY()),
                            new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(routeCities.getVisitDate()), id)
                    ));
        }
        return  resultList;
    }
    @Override
    public Long getCount() {
        return count;
    }

}
