package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OfferRepository extends BaseRepository<Offer, Long> {

    @Modifying
    @Query("update Offer off set off.approved=false where off.order.id = :id")
    void findOfferByOrderIdAndChangeStatus(@Param("id") Long id);

}
