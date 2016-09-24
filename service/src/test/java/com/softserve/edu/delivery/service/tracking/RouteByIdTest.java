package com.softserve.edu.delivery.service.tracking;


import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.dao.impl.OfferDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.RouteCityDaoImpl;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.service.impl.RouteServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych
 * Testing method getRouteById(Long id) from RouteServieImpl class
 * Method should return OrderRouteDto class instance which represent all
 * necessary tracking information about order
 * */
public class RouteByIdTest {

    private EntityManager em = Jpa.getEntityManager();

    private OrderDao orderDao = new OrderDaoImpl(); //Mockito.mock(OrderDaoImpl.class);
    private RouteCityDao routeCityDao = new RouteCityDaoImpl(); //Mockito.mock(RouteCityDaoImpl.class);
    private OfferDao offerDao = new OfferDaoImpl(); //Mockito.mock(OfferDaoImpl.class);

    private RouteService routeService =
            new RouteServiceImpl(orderDao, routeCityDao, offerDao);


    //test entities
    private Order testOrder = new Order();
    private City from = new City();
    private City to = new City();
    private User costumer = new User().setEmail("TestCostumer@domain.com").setPassword("Password123#");
    private User transporter = new User().setEmail("Transporter@domain.com").setPassword("Password123#");
    private Offer offer = new Offer();
    private Car car = new Car();

    @BeforeClass
    public void saveTestEntitiesToDatabase() {
        BigDecimal baggageHeight = new BigDecimal(1.11);
        BigDecimal baggageWidth = new BigDecimal(2.22);
        BigDecimal baggageLength = new BigDecimal(3.33);
        BigDecimal baggageWeight = new BigDecimal(4.44);
        BigDecimal baggagePrice = new BigDecimal(5.55);
        Timestamp registrationDate = new Timestamp(10_000_000L);
        Timestamp arrivalDate = new Timestamp(20_000_000L);
        String description = "TEST";
        testOrder.setHeight(baggageHeight);
        testOrder.setWidth(baggageWidth);
        testOrder.setLength(baggageLength);
        testOrder.setWeight(baggageWeight);
        testOrder.setPrice(baggagePrice);
        testOrder.setRegistrationDate(registrationDate);
        testOrder.setArrivalDate(arrivalDate);
        testOrder.setDescription(description);
        from.setCityName("Lviv");
        to.setCityName("Kyiv");
        testOrder.setCityFrom(from);
        testOrder.setCityTo(to);
        List<Order> orders = new ArrayList<>();
        orders.add(testOrder);

        costumer.setOrders(orders);
        List<Offer> offers = new ArrayList<>();
        offer.setOrder(this.testOrder);
        offer.setCar(car);
        offer.setApproved(true);
        offers.add(offer);
        testOrder.setOffers(offers);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        transporter.setCars(cars);
        car.setDriver(transporter);

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            new UserDaoImpl().save(this.costumer);
            new UserDaoImpl().save(this.transporter);
            new OrderDaoImpl().save(this.testOrder);
            new OfferDaoImpl().save(this.offer);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            Jpa.close();
            throw ex;
        }
    }

    @Test(enabled = false)
    public void getRouteByIdTest() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("select o from Order o where o.description = :description", Order.class);
            query.setParameter("description", "TEST");
            Order order = (Order) query.getSingleResult();
            Long orderId = order.getId();
            //test method
            OrderRouteDto dto = routeService.getRouteById(orderId);
            System.out.println(dto);
            tx.commit();
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }



    @AfterClass
    public void closeDatabaseProvider() {
        Jpa.close();
    }
}
