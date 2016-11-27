package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OfferInfoDto;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferServiceImpl implements OfferService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserAuthenticationDetails authenticationDetails;

    @Override
    public void addOffer(Long orderId, String email) {

        Order order = orderRepository.findOneOpt(orderId)
                .orElseThrow(() -> new IllegalArgumentException("no order found"));
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("no user found"));
        if(offerRepository.getOfferByOrderIdAndCarId(orderId, user.getCars().get(0).getCarId()).size() > 0) {
            throw new IllegalArgumentException("You can't create more than one offer");
        }
        Car car = user.getCars().get(0);
        Offer offer = new Offer();
        offer.setApproved(false);
        offer.setOrder(order);
        offer.setCar(car);
        offerRepository.save(offer);
    }

    @Transactional
    public OfferInfoDto findOfferInfo(Long offerId, String email) {
       return offerRepository
               .findOfferInfo(offerId)
               .filter(info -> info.getCustomerEmail().equals(email) || info.getDriverEmail().equals(email))
               .orElseThrow(() -> new IllegalArgumentException("Couldn't find offer info"));
    }

    @Override
    public List<OfferInfoDto> findDriverNamesByOrderId(Long orderId) {
        return offerRepository.findDriverNamesByOrderId(orderId);
    }

    @Override
    public void cancelOffer(Long orderId, String email) {
        offerRepository.delete(offerRepository.findOfferByOrderIdAndDriverId(orderId, email));
    }

    @Override
    public Long findOfferId(Long orderId, String email) {
        return offerRepository.findOfferIdByOrderIdAndDriverEmail(orderId, email);
    }

    @Override
    public void changeStatus(Long offerId, Boolean offerStatus, Long orderId) {
        offerRepository.findOfferByOrderIdAndChangeStatus(orderId);
        Offer offer = offerRepository.findOneOpt(offerId)
                .orElseThrow(() -> new IllegalArgumentException("No such offer with id: " + offerId));
        offer.setApproved(offerStatus);
        offerRepository.save(offer);

        Order order = orderRepository.findOneOpt(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No order found"));
        order.setOrderStatus(OrderStatus.APPROVED);
        orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDto> getOffersByOrderId(Long orderId) {
        return offerRepository
                .getAllOffersByOrderId(orderId)
                .stream()
                .map(OfferDto::offerToOfferDto)
                .collect(Collectors.toList());
    }
}
