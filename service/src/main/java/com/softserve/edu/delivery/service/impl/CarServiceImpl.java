package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.dto.CarDTO;
import com.softserve.edu.delivery.repository.CarRepository;
import com.softserve.edu.delivery.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    private CarDTO copyCarToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getCarId());
        carDTO.setVehicleFrontPhotoURL(car.getVehicleFrontPhotoURL());
        carDTO.setVehicleBackPhotoURL(car.getVehicleBackPhotoURL());
        carDTO.setVehicleHeight(car.getVehicleHeight());
        carDTO.setVehicleWidth(car.getVehicleWidth());
        carDTO.setVehicleLength(car.getVehicleLength());
        carDTO.setVehicleWeight(car.getVehicleWeight());
        carDTO.setVehicleName(car.getVehicleName());
        carDTO.setVehicleNumber(car.getVehicleNumber());
        carDTO.setVehicleVIN(car.getVehicleVIN());
        carDTO.setDriverEmail(car.getDriver().getEmail());
        carDTO.setActive(car.getActive());

        return carDTO;
    }

    private List<CarDTO> copyListCarToCarDTO(List<Car> carList){
        List<CarDTO> carDTOs = new ArrayList<>();
        carList.forEach(car -> carDTOs.add(copyCarToCarDTO(car)));

        return carDTOs;
    }

    @Override
    public Car findOne(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public CarDTO save(Car car) {
        return copyCarToCarDTO(carRepository.save(car));
    }

    @Override
    public void delete(Long id){
        carRepository.delete(id);
    }

    @Override
    public List<CarDTO> getCarsByDriver(String email) {
        return copyListCarToCarDTO(carRepository.findByDriverEmail(email));
    }
}
