package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Location;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.repository.LocationRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    private final Timestamp dummyTimestamp = parseTimestamp("2016-09-19");
    private final String dummyEmail = "dummy@dummy.com";

    @Before
    public void before() {
        doNothing().when(orderRepository).save(any(Order.class));

        when(userRepository.findOneOpt(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.findOneOpt(dummyEmail)).thenReturn(Optional.of(new User().setEmail(dummyEmail)));

        when(locationRepository.findOneOpt(any(String.class))).thenReturn(Optional.empty());
        when(locationRepository.findOneOpt("1")).thenReturn(Optional.of(new Location().setId("1")));
        when(locationRepository.findOneOpt("2")).thenReturn(Optional.of(new Location().setId("2")));
    }

    @Test
    public void addOrderSuccess() {
        orderService.addOrder(dummyDto(), dummyEmail);

        verify(orderRepository, times(1)).save(orderCaptor.capture());

        Order captured = orderCaptor.getValue();

        Assert.assertEquals(captured.getOrderStatus(), OrderStatus.OPEN);
        Assert.assertEquals(captured.getCustomer().getEmail(), dummyEmail);
        Assert.assertEquals(captured.getLocationFrom().getId(), "1");
        Assert.assertEquals(captured.getLocationTo().getId(), "2");

        Assert.assertEquals(captured.getArrivalDate(), dummyTimestamp);
        Assert.assertEquals(captured.getHeight(), Double.valueOf(23));
        Assert.assertEquals(captured.getLength(), Double.valueOf(15));
        Assert.assertEquals(captured.getWeight(), Double.valueOf(45));
        Assert.assertEquals(captured.getWidth(), Double.valueOf(13));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOrderDtoNull() {
        orderService.addOrder(null, dummyEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOrderNoUser() {
        orderService.addOrder(dummyDto(), dummyEmail + "dummyText");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOrderNoCityTo() {
        orderService.addOrder(dummyDto().setLocationFrom(new Location().setId("33")), dummyEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOrderNoCityFrom() {
        orderService.addOrder(dummyDto().setLocationTo(new Location().setId("33")), dummyEmail);
    }

    private OrderDto dummyDto() {
        return new OrderDto()
                .setArrivalDate(dummyTimestamp)
                .setLocationFrom(new Location().setId("1"))
                .setLocationTo(new Location().setId("2"))
                .setHeight(23d).setLength(15d)
                .setWeight(45d).setWidth(13d)
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
