package com.softserve.edu.delivery.domain;
/**
 * Author - Taras Kurdiukov
 */
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private long offerId;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@Column(name = "isApproved")
	private Boolean isApproved;

	public Offer(){
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Boolean getApproved() {
		return isApproved;
	}

	public void setApproved(Boolean approved) {
		isApproved = approved;
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
