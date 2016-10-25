package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.repository.CarRepository;
import com.softserve.edu.delivery.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car findOne(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }
}
