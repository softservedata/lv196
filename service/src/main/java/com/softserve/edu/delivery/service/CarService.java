package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.dto.CarDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface CarService {

    Car findOne(Long id);

    CarDTO save(Car car);

    CarDTO save(CarDTO carDTO);

    void delete(Long id);

    List<CarDTO> getCarsByDriver(String email);
}
