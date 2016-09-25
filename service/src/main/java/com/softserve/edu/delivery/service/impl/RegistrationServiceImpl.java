package com.softserve.edu.delivery.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.CarDTO;
import com.softserve.edu.delivery.dto.DriverRegistrationDTO;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.service.RegistrationService;
import com.softserve.edu.delivery.utils.TransactionManager;

public class RegistrationServiceImpl implements RegistrationService {

    private UserDao userDao;
    
    
    @Override
    public void register(UserRegistrationDTO userRegistrationDto) {

        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setPassword(userRegistrationDto.getPassword1());
        user.setPhone(userRegistrationDto.getPhoneNumber());
        user.setPassport(userRegistrationDto.getPassport());
        user.setPhotoUrl(userRegistrationDto.getPhotoURL());
        
        
        if(userRegistrationDto instanceof DriverRegistrationDTO) {
            DriverRegistrationDTO driverRegistrationDTO = (DriverRegistrationDTO) userRegistrationDto;
            List<Car> cars = new ArrayList<>();
            for(CarDTO carDTO : driverRegistrationDTO.getCarDtos()) {
                Car car = new Car();
                car.setVehicleName(carDTO.getVehicleName());
                car.setVehicleNumber(carDTO.getVehicleNumber());
                car.setVehicleVIN(carDTO.getVehicleVIN());
                car.setVehicleFrontPhotoURL(carDTO.getVehicleFrontPhotoURL());
                car.setVehicleBackPhotoURL(carDTO.getVehicleBackPhotoURL());
                car.setVehicleWeight(carDTO.getVehicleWeight());
                car.setVehicleLength(carDTO.getVehicleLength());
                car.setVehicleWidth(carDTO.getVehicleWidth());
                car.setVehicleHeight(carDTO.getVehicleHeight());
                
                cars.add(car);
                
            }
            user.setCars(cars);
        }
        TransactionManager.withTransaction(() -> {
            if (!userDao.exists(user.getEmail())) {
                userDao.save(user);
            } else {
                throw new IllegalArgumentException("User with given email already exists.");
            }
        });
    }

    
}
