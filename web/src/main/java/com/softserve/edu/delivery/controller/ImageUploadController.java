package com.softserve.edu.delivery.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "upload")
public class ImageUploadController {

    private final Logger logger = LoggerFactory.getLogger(ImageUploadController.class.getName());
    private final HttpHeaders responseHeaders = new HttpHeaders();
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "lv196java",
            "api_key", "111615263263157",
            "api_secret", "-HA9u2XJmgIOQTyf0VYNhBgA-fE"));

    private HttpStatus status = HttpStatus.OK;
    private String errorDetails;


    private ResponseEntity handleException(String errorDetails, String message) {
        logger.error(errorDetails + message);
        status = HttpStatus.BAD_REQUEST;
        responseHeaders.set("message", message);
        return new ResponseEntity(responseHeaders, status);
    }

    @RequestMapping(path = "deleteCloudPhoto/{path}/{publicId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCloudPhoto(@PathVariable String path,
                                    @PathVariable String publicId) {

        try {
            cloudinary.uploader().destroy(path + "/" + publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            errorDetails = "Exception while trying to delete cloud photo with id " + publicId +
                    " in imageUploadController.deleteCloudPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }

        return new ResponseEntity(responseHeaders, status);
    }


}
