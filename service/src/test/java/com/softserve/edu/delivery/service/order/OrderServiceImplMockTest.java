package com.softserve.edu.delivery.service.order;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Taras Kurdiukov on 21.09.2016.
 */
public class OrderServiceImplMockTest {

    @Mock
    OrderDao orderDao;

    @Mock
    UserDao userDao;

    @Mock
    CityDao cityDao;

    @Mock
    OfferDao offerDao;

    @InjectMocks
    OrderServiceImpl orderService;

    private final String email = "dummy@dummy.com";

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(enabled = false , expectedExceptions = IllegalArgumentException.class)
    public void testChangeStatusWhenOfferDoesntExist() {
//        Offer offer1 = new Offer();
//        Offer offer2 = new Offer();
        Long id = 1L;
//        offer1.setOfferId(id);
//        offer2.setOfferId(id);
        Boolean status = true;
//        Boolean status2 = false;

        when(offerDao.findOne(any(Long.class))).thenReturn(Optional.empty());

        orderService.changeStatus(id, status.toString());
    }

    @Test(enabled = false)
    public void testChangeStatus() throws Exception {
        Offer offer1 = new Offer();
        Long id = 1L;
        offer1.setOfferId(id);

        Boolean status = true;
        Boolean status2 = false;
        offer1.setApproved(status);

        when(offerDao.findOne(any(Long.class))).thenReturn(Optional.of(offer1));

        orderService.changeStatus(id, status.toString());

        Assert.assertEquals(offer1.getApproved(), status2);

        verify(offerDao).save(offer1);
    }

    @Test(enabled = false)
    public void testAddFeedback() throws Exception {

    }

}
