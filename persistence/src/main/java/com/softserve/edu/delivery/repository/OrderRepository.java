package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long>, JpaRepository<Order, Long> {
    List<Order> findOrderByCustomerEmailAndOrderStatusIn(String email, Collection<OrderStatus> status);
    Long removeById(Long id);
}
