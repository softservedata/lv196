package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    Logger logger = LoggerFactory.getLogger(NotificationController.class.getName());


    @RequestMapping(method = RequestMethod.GET)
    List<NotificationDto> all() {
        logger.info("Method NotificationController.all()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();
        String email = "martin@gmail.com";
        return notificationService.findAllNotificationByEmail(email);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeNotification(@PathVariable Long id) {
        notificationService.removeNotification(id);
    }

    @RequestMapping(path = "count", method = RequestMethod.GET)
    Integer countNotification() throws InterruptedException {
//        String email = authenticationDetails.getAuthenticatedUserEmail();
        String email = "martin@gmail.com";
        Integer amountNewNotification = Integer.valueOf(0);
        do{
            Thread.sleep(2000);
            amountNewNotification = this.notificationService.countNewNotification(email);
        }
        while (amountNewNotification == null);
        return amountNewNotification;
    }

}
