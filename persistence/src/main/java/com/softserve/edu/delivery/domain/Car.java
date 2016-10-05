package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CARS")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long carId;
	
	//vehicle brand name
	@Column(name = "vehicleName")
	private String vehicleName;
	
	//vehicle license plate
	@Column(name = "vehicleNumber")
	private String vehicleNumber;
	
	//vehicle registration card
	@Column(name = "vehicleVIN")
	private String vehicleVIN;
	
	//String type variable to store the URL to vehicle photos
	@Column(name = "vehicleFrontPhotoURL")
    private String vehicleFrontPhotoURL;
    @Column(name = "vehicleBackPhotoURL")
    private String vehicleBackPhotoURL;
    
    //Maximum carrying capacity of the vehicle
    @Column(name = "vehicleWeight")
    private BigDecimal vehicleWeight;
    
    //Vehicle maximum volume L W H
    @Column(name = "vehicleLength")
    private BigDecimal vehicleLength;
	@Column(name = "vehicleWidth")
	private BigDecimal vehicleWidth;
	@Column(name = "vehicleHeight")
	private BigDecimal vehicleHeight;
	
	//Class Car has one to many relationship to class User
	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "email")
	private User driver;

    @OneToMany(mappedBy = "car")
    private List<Offer> offers = new ArrayList<>();

    public Long getCarId() {
        return carId;
    }

    public Car setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public Car setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
        return this;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Car setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public Car setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
        return this;
    }

    public String getVehicleFrontPhotoURL() {
        return vehicleFrontPhotoURL;
    }

    public Car setVehicleFrontPhotoURL(String vehicleFrontPhotoURL) {
        this.vehicleFrontPhotoURL = vehicleFrontPhotoURL;
        return this;
    }

    public String getVehicleBackPhotoURL() {
        return vehicleBackPhotoURL;
    }

    public Car setVehicleBackPhotoURL(String vehicleBackPhotoURL) {
        this.vehicleBackPhotoURL = vehicleBackPhotoURL;
        return this;
    }

    public BigDecimal getVehicleWeight() {
        return vehicleWeight;
    }

    public Car setVehicleWeight(BigDecimal vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
        return this;
    }

    public BigDecimal getVehicleLength() {
        return vehicleLength;
    }

    public Car setVehicleLength(BigDecimal vehicleLength) {
        this.vehicleLength = vehicleLength;
        return this;
    }

    public BigDecimal getVehicleWidth() {
        return vehicleWidth;
    }

    public Car setVehicleWidth(BigDecimal vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
        return this;
    }

    public BigDecimal getVehicleHeight() {
        return vehicleHeight;
    }

    public Car setVehicleHeight(BigDecimal vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
        return this;
    }

    public User getDriver() {
        return driver;
    }

    public Car setDriver(User driver) {
        this.driver = driver;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public Car setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }
}
