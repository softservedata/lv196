package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OfferRepository extends BaseRepository<Offer, Long> {

    @Modifying
    @Query("update Offer off set off.approved=false where off.order.id = :id")
    void findOfferByOrderIdAndChangeStatus(@Param("id") Long id);

    @Query("select o from Offer o where o.order.id = :id")
    List<Offer> getAllOffersByOrderId(@Param("id")Long orderId);

    @Query("select o from Offer o where o.order.id = :id and o.car.id = :carId")
    List<Offer> getOfferByOrderIdAndCarId(@Param("id")Long orderId, @Param("carId")Long carId);

    Long countByOrderId(Long orderId);
}
