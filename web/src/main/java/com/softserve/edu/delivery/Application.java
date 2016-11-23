package com.softserve.edu.delivery;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.service.RouteCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {
//    implements CommandLineRunner

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private RouteCityService routeCityService;

//    @Override
//    public void run(String... strings) throws Exception
//    {
//        final List<Point> points = new ArrayList<>();
//
//        points.add(new Point(49.866071, 23.957118));
//        points.add(new Point(49.9602678,24.5536551));
//        points.add(new Point(50.0711054,25.1656323));
//        points.add(new Point(50.379910, 25.707483));
//        points.add(new Point(50.602637, 26.519247));
//        points.add(new Point(50.602061, 27.016033));
//        points.add(new Point(50.615450, 27.251681));
//        points.add(new Point(50.581687, 27.688400));
//        points.add(new Point(50.356229, 28.317024));
//        points.add(new Point(50.389874, 29.491531));
//        points.add(new Point(50.425987, 29.961527));
//        points.add(new Point(50.456188, 30.436237));
//
//        points.stream().forEach(p -> {
//            RouteCities routeCities = new RouteCities();
//            routeCities.setVisitDate(new Timestamp(new Date().getTime()));
//            routeCities.setX(p.getX());
//            routeCities.setY(p.getY());
//
//            Order order = new Order();
//            order.setId(1L);
//
//            routeCities.setOrder(order);
//
//            routeCityService.saveRouteCity(routeCities);
//        });
//    }
}