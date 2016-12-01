package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.dto.CarDTO;
import com.softserve.edu.delivery.repository.CarRepository;
import com.softserve.edu.delivery.service.CarService;
import com.softserve.edu.delivery.service.UserService;
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

    @Autowired
    private UserService userService;


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

    private Car carDTOToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setCarId(carDTO.getId());
        car.setVehicleName(carDTO.getVehicleName());
        car.setVehicleNumber(carDTO.getVehicleNumber());
        car.setVehicleVIN(carDTO.getVehicleVIN());
        car.setVehicleFrontPhotoURL(carDTO.getVehicleFrontPhotoURL());
        car.setVehicleBackPhotoURL(carDTO.getVehicleBackPhotoURL());
        car.setVehicleFrontPhotoURL(carDTO.getVehicleFrontPhotoURL());
        car.setVehicleWeight(carDTO.getVehicleWeight());
        car.setVehicleLength(carDTO.getVehicleLength());
        car.setVehicleWidth(carDTO.getVehicleWidth());
        car.setVehicleHeight(carDTO.getVehicleHeight());
        car.setDriver(userService.findOne(carDTO.getDriverEmail()));
        car.setActive(carDTO.getActive());

        return car;
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
    public CarDTO save(CarDTO carDTO) {
        return copyCarToCarDTO(carRepository.save(carDTOToCar(carDTO)));
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
