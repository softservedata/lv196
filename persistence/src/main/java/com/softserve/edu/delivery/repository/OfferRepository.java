package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.dto.OfferInfoDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OfferRepository extends BaseRepository<Offer, Long> {

    // rewrite but why?
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
    List<Offer> findOfferByOrderIdAndDriverId(@Param("id")Long orderId, @Param("email")String email);


    @Query("select new com.softserve.edu.delivery.dto.OfferInfoDto(" +
            "off.id, off.car.driver, ord.customer, ord.cityFrom.cityName, ord.cityTo.cityName, ord.arrivalDate) " +
            "from Offer off join off.order ord where off.offerId = :offerId")
    Optional<OfferInfoDto> findOfferInfo(@Param("offerId") Long offerId);
}