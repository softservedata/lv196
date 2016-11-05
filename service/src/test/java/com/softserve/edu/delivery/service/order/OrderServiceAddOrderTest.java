package com.softserve.edu.delivery.service.order;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.LocationDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.repository.CityRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
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
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

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
        doNothing().when(orderRepository).save(any(Order.class));

        when(userRepository.findOneOpt(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.findOneOpt(dummyEmail)).thenReturn(Optional.of(new User().setEmail(dummyEmail)));

        when(cityRepository.findOneOpt(any(Long.class))).thenReturn(Optional.empty());
        when(cityRepository.findOneOpt(1L)).thenReturn(Optional.of(new City().setCityId(1L)));
        when(cityRepository.findOneOpt(2L)).thenReturn(Optional.of(new City().setCityId(2L)));
    }

    @Test
    public void addOrderSuccess() {
        orderService.addOrder(dummyDto(), dummyEmail);

        verify(orderRepository, times(1)).save(orderCaptor.capture());

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
        orderService.addOrder(dummyDto().setLocationFrom(new LocationDto().setCityId(33L)), dummyEmail);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderNoCityFrom() {
        orderService.addOrder(dummyDto().setLocationTo(new LocationDto().setCityId(33L)), dummyEmail);
    }

    private OrderDto dummyDto() {
        return new OrderDto()
                .setArrivalDate(dummyTimestamp)
                .setLocationFrom(new LocationDto().setCityId(1L))
                .setLocationTo(new LocationDto().setCityId(2L))
                .setHeight(new Double(23)).setLength(new Double(15))
                .setWeight(new Double(45)).setWidth(new Double(13))
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
