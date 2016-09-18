package com.softserve.edu.delivery.dto;

import java.util.ArrayList;
import java.util.List;

public class DriverRegistrationDTO extends UserRegistrationDTO{

    List<CarDTO> carsDto;

    public DriverRegistrationDTO() {
        new ArrayList<CarDTO>();
    }

    public List<CarDTO> getCarDtos() {
        return carsDto;
    }

    public void setCarDtos(List<CarDTO> carDtos) {
        this.carsDto = carDtos;
    }
    
}
