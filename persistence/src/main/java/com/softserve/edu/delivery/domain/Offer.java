package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Author - Taras Kurdiukov
 */

@Entity
@Table(name = "OFFER")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private Long offerId;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "getApproved")
	private Boolean isApproved;

	public Offer(){
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Car getCar() {
		return car;
	}

	public Offer setCar(Car car) {
		this.car = car;
		return this;
	}

	public Boolean getApproved() {
		return isApproved;
	}

	public Offer setApproved(Boolean approved) {
		isApproved = approved;
		return this;
	}

	public Order getOrder() {
		return order;
	}

	public Offer setOrder(Order order) {
		this.order = order;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if ((obj == null) || this.getClass() != obj.getClass()) return false;
		Offer offer = (Offer) obj;
		return Objects.equals(offerId, offer.offerId);
	}

	@Override
	public int hashCode() {
		return (int) (37 * offerId);
	}
	
	@Override
	public String toString() {
		return "Offer [id = " + offerId + ", car = " + car + ", offer is: " + isApproved + "]";
	}
}
