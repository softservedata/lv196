package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.CarDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.CarService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

import java.util.List;

@RestController
@RequestMapping(path = "userProfile")
public class UserProfileController {

    private final HttpHeaders responseHeaders = new HttpHeaders();
    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class.getName());
    private final int MIN_PASSWORD_LENGTH = 4;
    private final int MAX_PASSWORD_LENGTH = 20;

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private HttpStatus status;
    private String errorDetails;

    private User userDTOToUser(UserProfileDto userProfileDto) {
        User user = userService.findOne(userProfileDto.getEmail());
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setPhone(userProfileDto.getPhone());
        user.setApproved(userProfileDto.getApproved());
        user.setBlocked(userProfileDto.getBlocked());
        user.setPhotoUrl(userProfileDto.getPhotoUrl());
        user.setRate(userProfileDto.getRate());
        user.setUserRole(userProfileDto.getUserRole());

        return user;
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

        return car;
    }

    private boolean isPasswordValid(CharSequence password0, CharSequence password1) {
        if (password0.length() >= MIN_PASSWORD_LENGTH && password0.length() <= MAX_PASSWORD_LENGTH) {
            if (password1.length() >= MIN_PASSWORD_LENGTH && password1.length() <= MAX_PASSWORD_LENGTH) {
                if (password0.equals(password1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private ResponseEntity handleException(String errorDetails, String message) {
        logger.error(errorDetails + message);
        status = HttpStatus.BAD_REQUEST;
        responseHeaders.set("message", message);
        return new ResponseEntity(responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "loggedUser", method = RequestMethod.GET)
    ResponseEntity<UserProfileDto> getLoggedUser() {

        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");

        UserProfileDto user = userService.getUser(authenticationDetails.getAuthenticatedUserEmail());

        if (user == null) {
            errorDetails = "Exception while trying to get logged user /n";
            //noinspection unchecked
            return handleException(errorDetails, "user not found");
        }

        //noinspection unchecked
        return new ResponseEntity(user, responseHeaders, status);
    }

    @RequestMapping(path = {"updateUser"}, method = RequestMethod.PUT)
    ResponseEntity updateUser(@RequestBody UserProfileDto userProfileDto) {
        logger.info("Before UserProfileController.update(userProfileDto)");
        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");

        try {
            userService.save(userDTOToUser(userProfileDto));
        } catch (Exception e) {
            errorDetails = "Exception while trying to update user with id " + userProfileDto.getEmail() +
                    " in UserProfileController.update(userProfileDto) ";
            return handleException(errorDetails, e.getMessage());
        }
        logger.info("After UserProfileController.update(userProfileDto)");
        return new ResponseEntity(responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "loggedUser/cars", method = RequestMethod.GET)
    ResponseEntity<CarDTO> getLoggedUserCars(@RequestParam("email") String email) {

        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");

        List<CarDTO> cars;

        try {
            cars = carService.getCarsByDriver(email);
        } catch (Exception e) {
            errorDetails = "Exception while trying to get cars of logged user /n";
            //noinspection unchecked
            return handleException(errorDetails, e.getMessage());
        }

        //noinspection unchecked
        return new ResponseEntity(cars, responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "addNewCar", method = RequestMethod.PUT)
    ResponseEntity<CarDTO> addNewCar(@RequestBody CarDTO carDTO) {
        logger.info("Before UserProfileController.addNewCar(CarDTO carDTO)");
        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");

        CarDTO carDTOsaved;
        try {
            carDTOsaved = carService.save(carDTOToCar(carDTO));
        } catch (Exception e) {
            errorDetails = "Exception while trying to add car for user with id " + carDTO.getDriverEmail() +
                    " in UserProfileController.addNewCar(CarDTO carDTO) ";
            //noinspection unchecked
            return handleException(errorDetails, e.getMessage());
        }
        logger.info("After UserProfileController.addNewCar(CarDTO carDTO)");
        //noinspection unchecked
        return new ResponseEntity(carDTOsaved, responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "updateCar", method = RequestMethod.PUT)
    ResponseEntity updateCar(@RequestBody CarDTO carDTO) {
        logger.info("Before UserProfileController.updateCar(CarDTO carDTO)");
        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");
        try {
            carService.save(carDTOToCar(carDTO));
        } catch (Exception e) {
            errorDetails = "Exception while trying to update car for user with id " + carDTO.getDriverEmail() +
                    " in UserProfileController.updateCar(CarDTO carDTO) ";
            return handleException(errorDetails, e.getMessage());
        }
        logger.info("After UserProfileController.updateCar(CarDTO carDTO)");
        return new ResponseEntity(responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "deleteCar/{carId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCar(@PathVariable long carId) {
        logger.info("Before UserProfileController.deleteCar(long carId)");
        status = HttpStatus.OK;
        responseHeaders.set("message", "OK");
        try {
            carService.delete(carId);
        } catch (Exception e) {
            errorDetails = "Exception while trying to delete car with id " + carId +
                    " in UserProfileController.addNewCar(CarDTO carDTO) ";
            return handleException(errorDetails, e.getMessage());
        }
        logger.info("After UserProfileController.deleteCar(long carId)");
        return new ResponseEntity(responseHeaders, status);
    }

    @RequestMapping(path = "updateUserPassword", method = RequestMethod.POST)
    ResponseEntity <String> changePassword(@RequestParam("current") CharSequence currentPassword,
                                  @RequestParam("new0") CharSequence newPassword0,
                                  @RequestParam("new1") CharSequence newPassword1) {

        String currentUserEmail = authenticationDetails.getAuthenticatedUserEmail();

        logger.info("Before UserProfileController.changePassword()");
        status = HttpStatus.BAD_REQUEST;

        try {
            if (userService.checkPassword(currentUserEmail, currentPassword)) {
                if (isPasswordValid(newPassword0, newPassword1)) {
                    userService.changePassword(currentUserEmail, newPassword0);
                    status = HttpStatus.OK;
                    responseHeaders.set("message", "Your password was successfully changed");
                } else {
                    responseHeaders.set("message", "Entered passwords do NOT match");
                    return ResponseEntity
                            .status(status)
                            .headers(responseHeaders)
                            .body("{\"message\": \"Entered passwords do NOT match\"}");
                }
            } else {
                responseHeaders.set("message", "Error");
                return ResponseEntity
                        .status(status)
                        .headers(responseHeaders)
                        .body("{\"message\": \"You have entered the WRONG password\"}");
            }
        } catch (Exception e) {
            errorDetails = "Exception while trying to change password for user " + currentUserEmail +
                    " in UserProfileController.changePassword() ";
            //noinspection unchecked
            return handleException(errorDetails, e.getMessage());
        }
        logger.info("After UserProfileController.changePassword()");
        return ResponseEntity
                .status(status)
                .headers(responseHeaders)
                .body("{\"message\": \"Your password was successfully changed\"}");
    }
}