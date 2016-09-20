package com.softserve.edu.delivery.service.tracking;


import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.dao.impl.OfferDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.RouteCityDaoImpl;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.service.impl.RouteServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych
 * Testing method getRouteById(Long id)
 * Method should return RouteDTO class instance which represent all
 * necessary tracking information about order
 * */
public class RouteByIdTest {

    private EntityManager em = Jpa.getEntityManager();

    private OrderDao orderDao = new OrderDaoImpl(); //Mockito.mock(OrderDaoImpl.class);
    private RouteCityDao routeCityDao = new RouteCityDaoImpl(); //Mockito.mock(RouteCityDaoImpl.class);
    private OfferDao offerDao = new OfferDaoImpl(); //Mockito.mock(OfferDaoImpl.class);

    private RouteService routeService =
            new RouteServiceImpl(orderDao, routeCityDao, offerDao);


    private Order testOrder = new Order();

    @BeforeClass
    public void saveTestOrderToDatabase() {
        BigDecimal baggageHeight = new BigDecimal(1.11);
        BigDecimal baggageWidth = new BigDecimal(2.22);
        BigDecimal baggageLength = new BigDecimal(3.33);
        BigDecimal baggageWeight = new BigDecimal(4.44);
        BigDecimal baggagePrice = new BigDecimal(5.55);
        Timestamp registrationDate = new Timestamp(10_000_000L);
        Timestamp arrivalDate = new Timestamp(20_000_000L);
        String description = "It's testing order";
        Order order = new Order();
        order.setHeight(baggageHeight);
        order.setWidth(baggageWidth);
        order.setLength(baggageLength);
        order.setWeight(baggageWeight);
        order.setPrice(baggagePrice);
        order.setRegistrationDate(registrationDate);
        order.setArrivalDate(arrivalDate);
        order.setDescription(description);
        City from = new City(); from.setCityName("Lviv");
        City to = new City(); to.setCityName("Kyiv");
        order.setCityFrom(from);
        order.setCityTo(to);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        User costumer = new User().setEmail("TestCostumer@domain.com").setPassword("Password123#");
        costumer.setOrders(orders);

        List<Offer> offers = new ArrayList<>();
        Offer offer = new Offer();
        offer.setOrder(this.testOrder);
        Car car = new Car();
        offer.setCar(car);
        offer.setApproved(true);
        offers.add(offer);

        order.setOffers(offers);

        User transporter = new User().setEmail("Transporter@domain.com").setPassword("Password123#");
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        transporter.setCars(cars);
        car.setUser(transporter);


    }

    @BeforeClass
    public void createTestOrders() {

    }

    @AfterClass
    public void removeTestOrders() {

    }



    @AfterClass
    public void closeDatabaseProvider() {
        Jpa.close();
    }
}
