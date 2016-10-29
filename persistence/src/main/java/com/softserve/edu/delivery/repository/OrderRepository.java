package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.container.OrderContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order, Long> {

    Optional<Order> findOrderByIdAndCustomerEmail(Long id, String email);

    Long removeById(Long id);

    @Query("select new com.softserve.edu.delivery.domain.container.OrderContainer(" +
            "ord, concat(off.car.driver.firstName, ' ', off.car.driver.lastName)) " +
            "from Offer off join off.order ord where ord.orderStatus = 'IN_PROGRESS' " +
            "and off.approved = 1 and ord.customer.email = :email")
    List<OrderContainer> findOrderInProgressByCustomerEmail(@Param("email") String email);

    @Query("select new com.softserve.edu.delivery.domain.container.OrderContainer(" +
            "ord, count(off.order.id)) from Offer off join off.order ord " +
            "where ord.orderStatus = 'OPEN' and off.car.driver.blocked = 0 " +
            "and ord.customer.email = :email group by ord.id")
    List<OrderContainer> findOrderOpenWithOffersByCustomerEmail(@Param("email") String email);

    @Query("select new com.softserve.edu.delivery.domain.container.OrderContainer(" +
            "ord, count(off.order.id)) from Order ord left join ord.offers off " +
            "where ord.orderStatus = 'OPEN' and ord.customer.email = :email " +
            "group by ord.id having count(off.order.id) = 0")
    List<OrderContainer> findOrderOpenWithoutOffersByCustomerEmail(@Param("email") String email);

    @Query("select o from Order o where o.orderStatus = 'OPEN'")
    List<Order> getAllOpenOrder();

    @Query("select new com.softserve.edu.delivery.domain.container.OrderContainer(" +
            "ord, concat(off.car.driver.firstName, ' ', off.car.driver.lastName), off.car.vehicleFrontPhotoURL) " +
            "from Offer off join off.order ord where ord.orderStatus = 'CLOSED' " +
            "and off.approved = 1 and ord.customer.email = :email")
    List<OrderContainer> getAllClosedOrderByCustomerEmail(@Param("email") String email);

    @Query("select o from Order o where o.orderStatus = 'OPEN' " +
            "and (:cityFromId is null or o.cityFrom.id = :cityFromId) " +
            "and (:cityToId is null or o.cityTo.id = :cityToId) " +
            "and (:weight is null or o.weight <= :weight) " +
            "and (:arrivalDate is null or o.arrivalDate >= :arrivalDate)")
    List<Order> getOrdersFiltered(@Param("cityFromId") Long cityFromId,
                                  @Param("cityToId") Long cityToId,
                                  @Param("weight") BigDecimal weight,
                                  @Param("arrivalDate") Timestamp arrivalDate);

    @Query("select off.order from Offer off where off.car.driver.email = :email " +
            "and off.order.orderStatus = 'OPEN'")
    List<Order> getOpenOrdersWithMyOffers(@Param("email") String email);

    @Query("select off.order from Offer off where off.car.driver.email = :email " +
            "and off.order.orderStatus = 'IN_PROGRESS'")
    List<Order> getMyOrdersInProgress(@Param("email") String email);

    @Query("select off.order from Offer off where off.car.driver.email = :email " +
            "and off.order.orderStatus = 'CLOSED'")
    List<Order> getMyOrdersClosed(@Param("email") String email);

    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);
}