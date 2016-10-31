package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping(path = "notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    Logger logger = LoggerFactory.getLogger(NotificationController.class.getName());
    Integer lastAmountNewNotification = 0;

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(method = RequestMethod.GET)
    List<NotificationDto> all() {
        logger.info("authenticationDetails.getAuthenticatedUserEmail() = " + authenticationDetails.getAuthenticatedUserEmail());
        return notificationService.findAllNotificationByEmail(authenticationDetails.getAuthenticatedUserEmail());
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeNotification(@PathVariable Long id) {
        notificationService.removeNotification(id);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "count", method = RequestMethod.GET)
    Integer countNotification() throws InterruptedException {
        Integer amountNewNotification = null;
        do{
            Thread.sleep(2000);
            amountNewNotification = this.notificationService.countNewNotification(authenticationDetails.getAuthenticatedUserEmail());
        }
        while (amountNewNotification == lastAmountNewNotification);
        lastAmountNewNotification = amountNewNotification;
        return amountNewNotification;
    }

}
