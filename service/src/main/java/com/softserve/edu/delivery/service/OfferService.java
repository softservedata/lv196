package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OfferInfoDto;

import java.util.List;

public interface OfferService {

    void addOffer(Long orderId, String email);
    void cancelOffer(Long orderId, String email);
    Long findOfferId(Long orderId, String email);
    OfferInfoDto findOfferInfo(Long offerId, String email);
    List<OfferInfoDto> findDriverNamesByOrderId(Long orderId);
    void changeStatus(Long offerId, Boolean offerStatus, Long orderId);
    List<OfferDto> getOffersByOrderId(Long id);
}
