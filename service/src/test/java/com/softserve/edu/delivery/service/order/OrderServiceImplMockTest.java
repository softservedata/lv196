package com.softserve.edu.delivery.service.order;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Taras Kurdiukov on 21.09.2016.
 */
public class OrderServiceImplMockTest {
    private FeedbackDao mockFDao = Mockito.mock(com.softserve.edu.delivery.dao.FeedbackDao.class);
    private OrderDao mockODao = Mockito.mock(com.softserve.edu.delivery.dao.OrderDao.class);
    private UserDao mockUDao = Mockito.mock(com.softserve.edu.delivery.dao.UserDao.class);
    private CityDao mockCDao = Mockito.mock(com.softserve.edu.delivery.dao.CityDao.class);
    private OfferDao mockOfDao = Mockito.mock(com.softserve.edu.delivery.dao.OfferDao.class);

    private FeedbackService fsi;
    private OrderService os;

    @BeforeClass
    public void injectMockDAO() {
        fsi = new FeedbackServiceImpl(mockFDao);
        os = new OrderServiceImpl(mockODao,mockUDao,mockCDao,mockFDao,mockOfDao);
    }

    @Test
    public void testChangeStatus() throws Exception {

    }
    @Test
    public void testAddFeedback() throws Exception {

    }

}
