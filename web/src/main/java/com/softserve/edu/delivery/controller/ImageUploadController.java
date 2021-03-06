package com.softserve.edu.delivery.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "upload")
public class ImageUploadController {

    @Value("${cloudinary_cloud_name}")
    String cloudName;

    @Value("${cloudinary_api_key}")
    String apiKey;

    @Value("${cloudinary_api_secret}")
    String apiSecret;

    private final Logger logger = LoggerFactory.getLogger(ImageUploadController.class.getName());

    private ResponseEntity handleException(String errorDetails, String message) {
        logger.error(errorDetails + message);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(status);
    }

    @RequestMapping(path = "deleteCloudPhoto/{path}/{publicId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCloudPhoto(@PathVariable String path,
                                    @PathVariable String publicId) {

        HttpStatus status = HttpStatus.OK;

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));

        try {
            cloudinary.uploader().destroy(path + "/" + publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            String errorDetails = "Exception while trying to delete cloud photo with id " + publicId +
                    " in imageUploadController.deleteCloudPhoto() ";
            return handleException(errorDetails, e.getMessage());
        }

        return new ResponseEntity(status);
    }

}
