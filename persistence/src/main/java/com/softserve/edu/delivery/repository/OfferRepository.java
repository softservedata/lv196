package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OfferRepository extends BaseRepository<Offer, Long> {

    @Query("select off.id from Offer off where off.order.id = :id and off.car.driver.email = :email")
    Long findOfferIdByOrderIdAndDriverEmail(@Param("id") Long orderId, @Param("email") String email);

    @Modifying
    @Query("update Offer off set off.approved=false where off.order.id = :id")
    void findOfferByOrderIdAndChangeStatus(@Param("id") Long id);

    @Query("select o from Offer o where o.order.id = :id and o.car.driver.blocked = false")
    List<Offer> getAllOffersByOrderId(@Param("id")Long orderId);

    @Query("select o from Offer o where o.order.id = :id and o.car.id = :carId")
    List<Offer> getOfferByOrderIdAndCarId(@Param("id")Long orderId, @Param("carId")Long carId);

    @Query("select o from Offer o where o.order.id = :id and o.car.driver.email = :email")
    List<Offer> findOfferByOrderIdAndDrverId(@Param("id")Long orderId, @Param("email")String email);
}
