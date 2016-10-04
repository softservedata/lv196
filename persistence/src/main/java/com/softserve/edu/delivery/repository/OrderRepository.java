package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order, Long>, JpaRepository<Order, Long> {

    List<Order> findOrderByCustomerEmailAndOrderStatusIn(String email, List<OrderStatus> status);

    default List<Order> findActiveOrdersByCustomerEmail(String email) {
        return findOrderByCustomerEmailAndOrderStatusIn(email,
                Arrays.asList(OrderStatus.OPEN, OrderStatus.IN_PROGRESS));
    }

    Long removeById(Long id);

    @Query("select concat(off.car.driver.firstName, ' ', off.car.driver.lastName) " +
            "from Offer off where off.order.id = :id and off.approved = true")
    Optional<String> findDriverNameByOrderId(@Param("id") Long id);
}
