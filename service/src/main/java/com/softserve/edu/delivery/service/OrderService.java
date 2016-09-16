package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import java.util.List;

public interface OrderService {
    List<OrderForListDto> findAllActiveOrders(String email, int page, int size);
    void addOrder(OrderForAddDto dto, String email);
}
