package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.domain.*;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(DBInit.class).init();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    @Service
    @Transactional
    public class DBInit {
        @Autowired
        private CityDao cityDao;
        @Autowired
        private UserDao userDao;
        @Autowired
        private OrderDao orderDao;
        @Autowired
        private CarDao carDao;
        @Autowired
        private OfferDao offerDao;

        public void init() {
            cityDao.save(new City("Lviv", null));
            cityDao.save(new City("Odesa", null));
            cityDao.save(new City("Kherson", null));

            userDao.save(new User().setEmail("martin@gmail.com").setFirstName("Martin").setLastName("Odersky"));
            userDao.save(new User().setEmail("max@gmail.com").setFirstName("Max").setLastName("Phantom").setUserRole(Role.DRIVER));
            userDao.save(new User().setEmail("long@gmail.com").setFirstName("Josh").setLastName("Long").setUserRole(Role.DRIVER));
            User customer = userDao.findOne("martin@gmail.com").orElseGet(null);
            User driver1 = userDao.findOne("max@gmail.com").orElseGet(null);
            User driver2 = userDao.findOne("long@gmail.com").orElseGet(null);

            Timestamp now = new Timestamp(new Date().getTime());


            orderDao.save(new Order()
                    .setArrivalDate(now).setRegistrationDate(now)
                    .setCityFrom(cityDao.findOne(2L).orElse(null)).setCityTo(cityDao.findOne(3L).orElse(null))
                    .setCustomer(customer).setOrderStatus(OrderStatus.OPEN));

            carDao.save(new Car().setDriver(driver1));
            Car car1 = carDao.findOne(1L).orElse(null);
            carDao.save(new Car().setDriver(driver2));
            Car car2 = carDao.findOne(2L).orElse(null);


            orderDao.save(new Order()
                    .setArrivalDate(now).setRegistrationDate(now)
                    .setCityFrom(cityDao.findOne(1L).orElse(null)).setCityTo(cityDao.findOne(2L).orElse(null))
                    .setCustomer(customer).setOrderStatus(OrderStatus.IN_PROGRESS));
            Order order1 = orderDao.findOne(2L).orElse(null);

            orderDao.save(new Order()
                    .setArrivalDate(now).setRegistrationDate(now)
                    .setCityFrom(cityDao.findOne(1L).orElse(null)).setCityTo(cityDao.findOne(3L).orElse(null))
                    .setCustomer(customer).setOrderStatus(OrderStatus.IN_PROGRESS));
            Order order2 = orderDao.findOne(3L).orElse(null);

            offerDao.save(new Offer().setApproved(true).setCar(car1).setOrder(order1));
            offerDao.save(new Offer().setApproved(true).setCar(car2).setOrder(order2));
        }
    }
}
