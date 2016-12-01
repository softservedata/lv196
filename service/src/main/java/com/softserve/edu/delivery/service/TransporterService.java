package com.softserve.edu.delivery.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.dto.*;
import org.springframework.data.domain.Pageable;

public interface TransporterService {
    List<PlaceDto> getAllPointsByOrderIdWithoutDate(Long id);
    OrderClosedDto findOneByIdAndOrderStatus(Long id);
    List<RoutesDto> getAllOrdersInProgress();
    List<OrderClosedDto> getAllOrdersClosedByDates(Timestamp min, Timestamp max);
    void setPoints(Long orderId, List<Point> points);
    void changeStatus(Long id, OrderStatus status);
    RoutesDto getMyRoute(Long id);
    List<Long> getAllIdInProgress();
}
