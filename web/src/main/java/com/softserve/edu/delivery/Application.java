package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.dao.impl.CityDaoImpl;
import com.softserve.edu.delivery.dao.impl.RegionDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

   /*     RegionDao regionDao = new RegionDaoImpl();
        System.out.println(regionDao.getRegionByState("Lviv state"));*/
    }
}
