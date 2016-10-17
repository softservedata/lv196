package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order, Long> {

    Optional<Order> findOrderByIdAndCustomerEmail(Long id, String email);

    List<Order> findOrderByCustomerEmailAndOrderStatus(String email, OrderStatus os);

    Long removeById(Long id);

    @Query("select concat(off.car.driver.firstName, ' ', off.car.driver.lastName) " +
            "from Offer off where off.order.id = :id and off.approved = true")
    Optional<String> findDriverNameByOrderId(@Param("id") Long id);

    @Query("select concat(off.car.driver.firstName, ' ', off.car.driver.lastName) " +
            "from Offer off where off.offerId = :id")
    Optional<String> findDriverNameByOfferId(@Param("id") Long id);

    @Query("select o from Order o where o.orderStatus = 'OPEN'")
    List<Order> getAllOpenOrder();

    @Query("select off.car.vehicleFrontPhotoURL from Offer off where off.offerId = :id")
    Optional<String> findCarPhotoByOrderId(@Param("id") Long id);

    @Query("select off.car.driver.rate from Offer off where off.offerId = :id")
    Optional<Integer> findRateByOfferId(@Param("id") Long id);

    @Query("select o from Order o where o.orderStatus = 'OPEN' " +
            "and (:cityFromId is null or o.cityFrom.id = :cityFromId) " +
            "and (:cityToId is null or o.cityTo.id = :cityToId) " +
            "and (:weight is null or o.weight <= :weight) " +
            "and (:arrivalDate is null or o.arrivalDate >= :arrivalDate)")
    List<Order> getOrdersFiltered(@Param("cityFromId") Long cityFromId,
                                  @Param("cityToId") Long cityToId,
                                  @Param("weight") BigDecimal weight,
                                  @Param("arrivalDate") Timestamp arrivalDate);
}