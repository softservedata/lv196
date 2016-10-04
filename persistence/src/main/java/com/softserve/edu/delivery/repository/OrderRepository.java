package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends BaseRepository<Order, Long>, JpaRepository<Order, Long> {
        Long removeById(Long id);
}
