package com.softserve.edu.delivery.service.tracking;


import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.dao.impl.OfferDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.RouteCityDaoImpl;
import com.softserve.edu.delivery.service.RouteService;
import com.softserve.edu.delivery.service.impl.RouteServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.persistence.EntityManager;

/**
 * @author Petro Shtenovych
 * Testing method getRouteById(Long id):RouteDTO
 * */
public class RouteByIdTest {

    private EntityManager em;

    private OrderDao orderDaoMock = Mockito.mock(OrderDaoImpl.class);
    private RouteCityDao routeCityDaoMock = Mockito.mock(RouteCityDaoImpl.class);
    private OfferDao offerDaoMock = Mockito.mock(OfferDaoImpl.class);

    private RouteService routeService =
            new RouteServiceImpl(orderDaoMock, routeCityDaoMock, offerDaoMock);

    @BeforeClass
    public void initDatabaseProvider() {
        em = Jpa.getEntityManager();
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
