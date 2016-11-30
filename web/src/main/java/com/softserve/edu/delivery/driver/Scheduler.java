package com.softserve.edu.delivery.driver;

import com.softserve.edu.delivery.service.PointService;
import com.softserve.edu.delivery.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class Scheduler {
    private  List<Long> ids = new ArrayList<>();
    private  boolean flag = false;

    @Autowired
    private PointService pointService;
    @Autowired
    private TransporterService teTransporterService;

    public Scheduler() {
    }

    public  synchronized void setFlag(boolean flags) {
        flag = flags;
        getAllInProgress();
    }

    public void getAllInProgress() {
        ids = teTransporterService.getAllIdInProgress();
    }

    @Scheduled(fixedRate = 10000)
    public void update() {
        if (flag) {
            for(Long id : ids) {
                pointService.update(id);
            }
        }
    }
}
