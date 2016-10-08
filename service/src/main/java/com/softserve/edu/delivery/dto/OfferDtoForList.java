package com.softserve.edu.delivery.dto;
import com.softserve.edu.delivery.domain.Offer;
import java.util.Objects;


public class OfferDtoForList {


    private Long offerId;
    private Long carId;
    private Long orderId;
    private boolean approved;

    public OfferDtoForList(){}

    public static OfferDtoForList offerToOfferDto(Offer offer){
        OfferDtoForList offerDto = new OfferDtoForList();
        offerDto.setOfferId(offer.getOfferId())
                .setCarId(offer.getCar().getCarId())
                .setOrderId(offer.getOrder().getId())
                .setApproved(offer.isApproved());
        return offerDto;
    }

    public Long getOfferId() {
        return offerId;
    }

    public OfferDtoForList setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public OfferDtoForList setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OfferDtoForList setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public OfferDtoForList setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDtoForList offerDtoForList = (OfferDtoForList) o;
        return Objects.equals(offerId, offerDtoForList.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
