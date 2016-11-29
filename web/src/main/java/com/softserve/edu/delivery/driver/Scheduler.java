package com.softserve.edu.delivery.driver;

import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.softserve.edu.delivery.domain.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@EnableScheduling()
public class Scheduler {
    private static List<Point> points = new ArrayList<>();
    private static Long id;
    private static int count = 0;
    private static boolean flag = false;

    @Autowired
    private PointService pointService;

    public Scheduler() {
    }
    public static int getCount(){
        return count;
    }

    public static void setId(Long idOrder) {
        id = idOrder;
    }

    public static void setFlag(boolean flags) {
        flag = flags;
    }

    public static void getPoints(List<PlaceDto> list) {
        for(PlaceDto p : list){
            points.add(p.getPoint());
        }
    }
    @Scheduled(fixedRate = 10000)
    public void update() {
        if (flag) {
            pointService.update(points.get(count++), id);
        }
        if (count == points.size()) {
            flag = false;
        }
    }
}
