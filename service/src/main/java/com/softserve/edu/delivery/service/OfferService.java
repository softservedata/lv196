package com.softserve.edu.delivery.service;

/**
 * Created by Ivan Synyshyn on 11.10.2016.
 */
public interface OfferService {

    //As transporter I want to add Offer on order.
    void addOffer(Long orderId, String email);
}
