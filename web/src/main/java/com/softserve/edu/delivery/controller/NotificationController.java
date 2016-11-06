package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping(path = "notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

//    Logger logger = LoggerFactory.getLogger(NotificationController.class.getName());
    Integer lastAmountNewNotification = null;

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(method = RequestMethod.GET)
    List<NotificationDto> all(Principal principal) {
        return notificationService.findAllNotificationByEmail(principal.getName());
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeNotification(@PathVariable Long id) {
        notificationService.removeNotification(id);
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "count", method = RequestMethod.GET)
    Integer countNotification(Principal principal) throws InterruptedException {
        Integer amountNewNotification = null;
        do{
            Thread.sleep(2000);
            amountNewNotification = this.notificationService.countNewNotification(principal.getName());
        }
        while (amountNewNotification == lastAmountNewNotification);
        lastAmountNewNotification = amountNewNotification;
        return amountNewNotification;

    }

}
