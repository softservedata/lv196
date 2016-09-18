package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.DriverRegistrationDto;
import com.softserve.edu.delivery.dto.UserRegistrationDto;

public interface RegistrationService {

    void register(UserRegistrationDto userRegistrationDto);
//  void register(DriverRegistrationDto driverRegistrationDto);
}
