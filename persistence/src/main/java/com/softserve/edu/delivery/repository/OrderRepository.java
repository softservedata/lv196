package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order, Long> {

    Optional<Order> findOrderByIdAndCustomerEmail(Long id, String email);

    Long removeById(Long id);

    @Query("select new com.softserve.edu.delivery.dto.OrderDto(" +
            "ord, concat(off.car.driver.firstName, ' ', off.car.driver.lastName), off.offerId) " +
            "from Offer off join off.order ord where ord.orderStatus in ('IN_PROGRESS', 'APPROVED') " +
            "and off.approved = 1 and ord.customer.email = :email")
    List<OrderDto> findOrderInProgressByCustomerEmail(@Param("email") String email);

    @Query("select new com.softserve.edu.delivery.dto.OrderDto(" +
            "ord, count(off.order.id)) from Order ord left join ord.offers off " +
            "left join off.car c left join c.driver d " +
            "where (d.blocked = 0 or d.blocked is null) and ord.orderStatus = 'OPEN' " +
            "and ord.customer.email = :email group by ord.id")
    List<OrderDto> findOrderOpenByCustomerEmail(@Param("email") String email);

    @Query("select o from Order o where o.orderStatus = 'OPEN'")
    List<Order> getAllOpenOrder(Pageable pageable);

    @Query("select count(o) from Order o where o.orderStatus = 'OPEN'")
    long getCountOfOrders();

    @Query("select new com.softserve.edu.delivery.dto.OrderDto(" +
            "ord, concat(off.car.driver.firstName, ' ', off.car.driver.lastName), off.car.vehicleFrontPhotoURL) " +
            "from Offer off join off.order ord where ord.orderStatus = 'CLOSED' " +
            "and off.approved = 1 and ord.customer.email = :email order by ord.arrivalDate DESC")
    List<OrderDto> getAllClosedOrderByCustomerEmail(@Param("email") String email);

    @Query("select o from Order o where o.orderStatus = 'OPEN' " +
            "and (:locationFromId is null or o.locationFrom.id = :locationFromId) " +
            "and (:locationToId is null or o.locationTo.id = :locationToId) " +
            "and (:weight is null or o.weight <= :weight) " +
            "and (:arrivalDate is null or o.arrivalDate >= :arrivalDate)")
    Page<Order> getOrdersFiltered(@Param("locationFromId") String locationFromId,
                                  @Param("locationToId") String locationToId,
                                  @Param("weight") Double weight,
                                  @Param("arrivalDate") Timestamp arrivalDate, Pageable pageable);

    @Query("select off.order from Offer off where off.car.driver.email = :email " +
            "and off.order.orderStatus = 'CLOSED'")
    List<Order> getMyOrdersClosed(@Param("email") String email);

    @Query("select off.order from Offer off where off.car.driver.email = :email " +
            "and off.order.orderStatus = :status")
    List<Order> getMyOrdersByStatus(@Param("email") String email,
                                    @Param("status") OrderStatus status);

    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);

    @Query("select count(o.id) from Order o where o.orderStatus = 'CLOSED'" +
            "and o.arrivalDate between ?1 and ?2")
    Long countByArrivalDate(@Param("arrivalDate") Timestamp dateFrom, @Param("arrivalDate") Timestamp dateTo);
}
