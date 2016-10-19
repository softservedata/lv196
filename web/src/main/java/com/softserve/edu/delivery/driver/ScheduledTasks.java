package com.softserve.edu.delivery.driver;

import com.softserve.edu.delivery.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.softserve.edu.delivery.domain.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natalia on 12.10.2016.
 */
@Component
public class ScheduledTasks {
    private static final List<Point> points = new ArrayList<>();
    private int count = 0;
    @Autowired
    private PointService pointService;
    public ScheduledTasks(){
        points.add(new Point(49.866071, 23.957118));
        points.add(new Point(49.9602678,24.5536551));
        points.add(new Point(50.0711054,25.1656323));
        points.add(new Point(50.379910, 25.707483));
        points.add(new Point(50.602637, 26.519247));
        points.add(new Point(50.602061, 27.016033));
        points.add(new Point(50.615450, 27.251681));
        points.add(new Point(50.581687, 27.688400));
        points.add(new Point(50.356229, 28.317024));
        points.add(new Point(50.389874, 29.491531));
        points.add(new Point(50.425987, 29.961527));
        points.add(new Point(50.456188, 30.436237));
    }
    @Scheduled(fixedRate = 5000)
    public Point getPoints() {
        if(count == points.size()){
            count = 0;
            pointService.deleteAll();
        }
        Point point = points.get(count++);
        System.out.println(point);
        pointService.savePleace(point);

        return point;

    }
}
