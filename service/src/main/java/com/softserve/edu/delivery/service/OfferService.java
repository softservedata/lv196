package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OfferInfoDto;

import java.util.List;

/**
 * Created by Ivan Synyshyn on 11.10.2016.
 */
public interface OfferService {

    //As transporter I want to add Offer on order.
    void addOffer(Long orderId, String email);
    void cancelOffer(Long orderId, String email);
    Long findOfferId(Long orderId, String email);

    OfferInfoDto findOfferInfo(Long offerId, String email);

    /**
     * Author - Taras Kurdiukov
     */
    //Method for user story - "As customer I want to change offer status."
    void changeStatus(Long offerId, Boolean offerStatus, Long orderId);

    List<OfferDto> getOffersByOrderId(Long id);
}
