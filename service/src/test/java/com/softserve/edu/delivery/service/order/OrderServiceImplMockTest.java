package com.softserve.edu.delivery.service.order;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.OrderService;
import org.mockito.Mockito;
import org.testng.annotations.Test;

/**
 * Created by Taras Kurdiukov on 21.09.2016.
 */
public class OrderServiceImplMockTest {
    private FeedbackDao mockFDao = Mockito.mock(com.softserve.edu.delivery.dao.FeedbackDao.class);
    private OrderDao mockODao = Mockito.mock(com.softserve.edu.delivery.dao.OrderDao.class);
    private UserDao mockUDao = Mockito.mock(com.softserve.edu.delivery.dao.UserDao.class);
    private CityDao mockCDao = Mockito.mock(com.softserve.edu.delivery.dao.CityDao.class);

    private FeedbackService fsi;
    private OrderService os;

    public OrderServiceImplMockTest(){

    }

    @Test
    public void testName() throws Exception {

    }

}
