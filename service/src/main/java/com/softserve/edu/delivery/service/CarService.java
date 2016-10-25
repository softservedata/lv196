package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CarService {

    Car findOne(Long id);

    void save(Car car);
}
