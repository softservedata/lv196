package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ivan Synyshyn on 11.10.2016.
 */

@Service
@Transactional
public class OfferServiceImpl implements OfferService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    UserAuthenticationDetails authenticationDetails;

    @Autowired
    OfferRepository offerRepository;

    @Override
    public void addOffer(Long orderId, String email) {
        Order order = orderDao.findOne(orderId).get();
        User user = userDao.findOne(email)
                .orElseThrow(() -> new IllegalArgumentException("no user found"));
        Car car = user.getCars().get(0);
        Offer offer = new Offer();
        offer.setApproved(false);
        offer.setOrder(order);
        offer.setCar(car);

        offerRepository.save(offer);

    }
}