package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.Objects;

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
	@Column(name = "document")
	private String document;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Car() {
	}

	public User getUser() {
		return user;
	}

	public Car setUser(User user) {
		this.user = user;
		return this;
	}

	public Long getCarId() {
		return carId;
	}

	public Car setCarId(Long carId) {
		this.carId = carId;
		return this;
	}

	public Double getWidth() {
		return width;
	}

	public Car setWidth(Double width) {
		this.width = width;
		return this;
	}

	public Double getHeight() {
		return height;
	}

	public Car setHeight(Double height) {
		this.height = height;
		return this;
	}

	public Double getLength() {
		return length;
	}

	public Car setLength(Double length) {
		this.length = length;
		return this;
	}

	public Double getWeight() {
		return weight;
	}

	public Car setWeight(Double weight) {
		this.weight = weight;
		return this;
	}

	public String getDocument() {
		return document;
	}

	public Car setDocument(String document) {
		this.document = document;
		return this;
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
		return Objects.equals(carId, car.carId);
	}

	@Override
	public String toString() {
		return "Car id: " + carId + "\nWidth: " + width + ", height: " + height + ", length: " + length + 
		"\nWeight: " + weight + "\nDocument: " + document + "\nUser: " + user.toString();
	}
}
