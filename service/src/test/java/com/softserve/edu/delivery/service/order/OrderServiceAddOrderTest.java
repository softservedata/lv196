package com.softserve.edu.delivery.service.order;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.repository.CityRepository;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class OrderServiceAddOrderTest {
    @Mock
    OrderDao orderDao;

    @Mock
    UserDao userDao;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Captor
    ArgumentCaptor<Order> orderCaptor;

    private final Timestamp dummyTimestamp = parseTimestamp("2016-09-19");
    private final String dummyEmail = "dummy@dummy.com";

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void mockDaos() {
        doNothing().when(orderDao).save(any(Order.class));

        when(userDao.findOne(any(String.class))).thenReturn(Optional.empty());
        when(userDao.findOne(dummyEmail)).thenReturn(Optional.of(new User().setEmail(dummyEmail)));

        when(cityRepository.findOneOpt(any(Long.class))).thenReturn(Optional.empty());
        when(cityRepository.findOneOpt(1L)).thenReturn(Optional.of(new City().setCityId(1L)));
        when(cityRepository.findOneOpt(2L)).thenReturn(Optional.of(new City().setCityId(2L)));
    }

    @Test
    public void addOrderSuccess() {
        orderService.addOrder(dummyDto(), dummyEmail);

        verify(orderDao, times(1)).save(orderCaptor.capture());

        Order captured = orderCaptor.getValue();

        Assert.assertEquals(captured.getOrderStatus(), OrderStatus.OPEN);
        Assert.assertEquals(captured.getCustomer().getEmail(), dummyEmail);
        Assert.assertEquals((long) captured.getCityFrom().getCityId(), 1L);
        Assert.assertEquals((long) captured.getCityTo().getCityId(), 2L);

        Assert.assertEquals(captured.getArrivalDate(), dummyTimestamp);
        Assert.assertEquals(captured.getHeight(), new BigDecimal(23));
        Assert.assertEquals(captured.getLength(), new BigDecimal(15));
        Assert.assertEquals(captured.getWeight(), new BigDecimal(45));
        Assert.assertEquals(captured.getWidth(), new BigDecimal(13));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderDtoNull() {
        orderService.addOrder(null, dummyEmail);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderNoUser() {
        orderService.addOrder(dummyDto(), dummyEmail + "dummyText");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderNoCityTo() {
        orderService.addOrder(dummyDto().setCityIdTo(33L), dummyEmail);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderNoCityFrom() {
        orderService.addOrder(dummyDto().setCityIdFrom(33L), dummyEmail);
    }

    private OrderForAddDto dummyDto() {
        return new OrderForAddDto()
                .setArrivalDate(dummyTimestamp)
                .setCityIdFrom(1L).setCityIdTo(2L)
                .setHeight(new BigDecimal(23)).setLength(new BigDecimal(15))
                .setWeight(new BigDecimal(45)).setWidth(new BigDecimal(13))
                .setDescription("dummy desc");
    }

    private Timestamp parseTimestamp(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Timestamp(dateFormat.parse(date).getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
