package com.softserve.edu.delivery.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARS")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long carId;
	@Column(name = "width")
	private Double width;
	@Column(name = "height")
	private Double height;
	@Column(name = "length")
	private Double length;
	@Column(name = "weight")
	private Double weight;

	@OneToOne
	@JoinColumn(name = "document_id")
	private Document document;

	@Column(name = "isApproved")
	private Boolean isApproved;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Car() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocumentId(Document document) {
		this.document = document;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carId);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Car car = (Car) obj;
		return Objects.equals(carId, car.carId) && Objects.equals(document, car.document);
	}

	@Override
	public String toString() {
		return "Car id: " + carId + "\nWidth: " + width + ", height: " + height + ", length: " + length + "\nWeight: " + weight + 
			"\nDocument: " + document.toString() + "\nUser: " + user.toString();
	}
}
