package com.softserve.edu.delivery.dto;

import java.util.List;

public class OrderTrackingDTO {
    
    private OrderDto orderDto;
    
    private List<RouteCityDTO> routeCityDTOs;

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public List<RouteCityDTO> getRouteCityDTOs() {
        return routeCityDTOs;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public void setRouteCityDTOs(List<RouteCityDTO> routeCityDTOs) {
        this.routeCityDTOs = routeCityDTOs;
    }
    
}