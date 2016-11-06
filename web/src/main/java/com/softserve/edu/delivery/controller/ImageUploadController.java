package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping(path = "upload")
public class ImageUploadController {

    private final Logger logger = LoggerFactory.getLogger(ImageUploadController.class.getName());
    private final HttpHeaders responseHeaders = new HttpHeaders();
    private final Map<String, String> response = new HashMap<>();
    private final String SERVER_PATH_TO_USERS_UPLOAD = "/uploads/users/";
    private final String SERVER_PATH_TO_CARS_UPLOAD = "/uploads/cars/";

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private HttpStatus status = HttpStatus.OK;
    private String realPathToUploads;
    private String errorDetails;
    private User user;
    private Car car;

    private File multipartToFile(MultipartFile multipart, String prefix, String path) throws IOException {
        realPathToUploads = request.getServletContext().getRealPath(path);
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdirs();
        }
        File convFile = new File(realPathToUploads + prefix + "_" + multipart.getOriginalFilename());

        multipart.transferTo(convFile);

        return convFile;
    }

    private void deleteOldPhoto(String path) {
        try {
            realPathToUploads = request.getServletContext().getRealPath("");
            Files.delete(Paths.get(realPathToUploads + path));
        } catch (IOException e) {
            logger.error("Exception while trying to delete user photo " + path +
                    " in imageUploadController.deleteOldPhoto() " + e.getMessage());
        }
    }

    private String saveCarPhoto(MultipartFile file, String side) throws IOException {
        String carPhotoPath;
        String photoUrl = null;

        if (side.equals("front")) {
            photoUrl = car.getVehicleFrontPhotoURL();
        } else if (side.equals("back")) {
            photoUrl = car.getVehicleBackPhotoURL();
        }

        if (photoUrl != null) {
            if (user.getPhotoUrl() != null) {
                deleteOldPhoto(photoUrl);
            }
        }

        File carPhoto = multipartToFile(file, user.getEmail() + "_" + side + "_photo_car_id_" + car.getCarId(),
                SERVER_PATH_TO_CARS_UPLOAD);
        carPhotoPath = SERVER_PATH_TO_CARS_UPLOAD + carPhoto.getName();

        if (side.toLowerCase().equals("front")) {
            car.setVehicleFrontPhotoURL(carPhotoPath);
        } else if (side.toLowerCase().equals("back")) {
            car.setVehicleBackPhotoURL(carPhotoPath);
        }

        return carPhotoPath;
    }

    private ResponseEntity handleException(String errorDetails, String message) {
        logger.error(errorDetails + message);
        status = HttpStatus.BAD_REQUEST;
        responseHeaders.set("message", message);
        return new ResponseEntity(responseHeaders, status);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "userPhoto", method = RequestMethod.POST)
    ResponseEntity uploadUserPhoto(@RequestParam("file") MultipartFile file) {
        responseHeaders.set("message", "OK");

        user = userService.findOne(authenticationDetails.getAuthenticatedUserEmail());

        if (user.getPhotoUrl() != null) {
            deleteOldPhoto(user.getPhotoUrl());
        }

        try {
            File userPhoto = multipartToFile(file, user.getEmail(), SERVER_PATH_TO_USERS_UPLOAD);
            user.setPhotoUrl(SERVER_PATH_TO_USERS_UPLOAD + userPhoto.getName());
        } catch (IOException e) {
            errorDetails = "Exception while trying to save user photo " + user.getEmail() +
                    " in imageUploadController.uploadUserPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }

        try {
            userService.save(user);
        } catch (NoSuchElementException e) {
            errorDetails = "Exception while trying to save user " + user.getEmail() +
                    " in imageUploadController.uploadUserPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }
        return new ResponseEntity(responseHeaders, status);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @SuppressWarnings("unchecked")
    @RequestMapping(path = "carPhoto", method = RequestMethod.POST)
    ResponseEntity uploadCarPhoto(@RequestParam("file") MultipartFile file,
                                  @RequestParam("carId") long carId,
                                  @RequestParam("side") String side) {
        String carPhotoPath;
        responseHeaders.set("message", "OK");

        user = userService.findOne(authenticationDetails.getAuthenticatedUserEmail());

        car = carService.findOne(carId);

        try {
            carPhotoPath = saveCarPhoto(file, side);
        } catch (IOException e) {
            errorDetails = "Exception while trying to save car front photo " + user.getEmail() +
                    " in imageUploadController.uploadCarPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }

        try {
            carService.save(car);
        } catch (NoSuchElementException e) {
            errorDetails = "Exception while trying to persist car " + car.getCarId() +
                    " in imageUploadController.uploadCarPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }

        response.put("carPhotoPath", carPhotoPath);
        return new ResponseEntity(response, responseHeaders, status);
    }
}
