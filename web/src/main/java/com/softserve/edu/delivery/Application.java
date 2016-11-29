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
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private RouteCityService routeCityService;

}