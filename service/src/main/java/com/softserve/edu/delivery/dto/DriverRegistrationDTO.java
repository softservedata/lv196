package com.softserve.edu.delivery.dto;

import java.util.List;

public class DriverRegistrationDto extends UserRegistrationDto{

    List<CarDto> carDtos;

    public DriverRegistrationDto() {
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }
    
}
