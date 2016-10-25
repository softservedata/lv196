package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.service.CarService;
import com.softserve.edu.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "upload")
public class ImageUploadController {

    private final Logger logger = LoggerFactory.getLogger(ImageUploadController.class.getName());
    private final String SERVER_PATH_TO_USERS_UPLOAD = "/uploads/users/";
    private final String SERVER_PATH_TO_CARS_UPLOAD = "/uploads/cars/";
    private final Map<String, String> response = new HashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private HttpServletRequest request;
    private HttpStatus status;

    private File multipartToFile(MultipartFile multipart, String prefix, String path) throws IOException {
        String realPathToUploads = request.getServletContext().getRealPath(path);
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdirs();
        }
        File convFile = new File(realPathToUploads + prefix + "_" + multipart.getOriginalFilename());

        multipart.transferTo(convFile);

        return convFile;
    }

    @RequestMapping(path = "userPhoto", method = RequestMethod.POST)
    ResponseEntity uploadUserPhoto(@RequestParam("file") MultipartFile file) {
        status = HttpStatus.OK;
        response.put("message", "OK");

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findOne(principal.getUsername());

        try {
            File userPhoto = multipartToFile(file, user.getEmail(), SERVER_PATH_TO_USERS_UPLOAD);
            user.setPhotoUrl(SERVER_PATH_TO_USERS_UPLOAD + userPhoto.getName());

        } catch (IOException e) {
            logger.info("Exception while trying to save user photo " + user.getEmail() +
                    " in feedbackService.uploadUserPhoto() " + e.getMessage());
            status = HttpStatus.NOT_FOUND;
            response.put("message", e.getMessage());
            return new ResponseEntity(response, status);
        }

        try {
            userService.save(user);
        } catch (NoSuchElementException e) {
            status = HttpStatus.BAD_REQUEST;
            response.put("message", e.getMessage());
        }
        return new ResponseEntity(response, status);
    }

    @RequestMapping(path = "carPhoto", method = RequestMethod.POST)
    ResponseEntity uploadCarPhoto(@RequestParam("file") MultipartFile file,
                                  @RequestParam("carId") long carId,
                                  @RequestParam("side") String side) {
        status = HttpStatus.OK;
        response.put("message", "OK");

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findOne(principal.getUsername());

        Car car = carService.findOne(carId);

        try {
            File userPhoto = multipartToFile(file, user.getEmail() + "_" + side + "_photo_car_id_" + car.getCarId(),
                    SERVER_PATH_TO_CARS_UPLOAD);
            car.setVehicleFrontPhotoURL(SERVER_PATH_TO_CARS_UPLOAD + userPhoto.getName());

        } catch (IOException e) {
            logger.info("Exception while trying to save car front photo " + user.getEmail() +
                    " in feedbackService.uploadCarPhoto() " + e.getMessage());
            status = HttpStatus.NOT_FOUND;
            response.put("message", e.getMessage());
            return new ResponseEntity(response, status);
        }

        try {
            carService.save(car);
        } catch (NoSuchElementException e) {
            logger.info("Exception while trying to persist car " + car.getCarId() +
                    " in feedbackService.uploadCarPhoto() " + e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            response.put("message", e.getMessage());
        }
        return new ResponseEntity(response, status);
    }


}
