package com.softserve.edu.delivery.domain;

import java.math.BigDecimal;

import javax.persistence.*;

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
	@JoinColumn(name = "driver_id")
	private User driver;

	public Car() {
	}

    public Long getCarId() {
        return carId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public String getVehicleFrontPhotoURL() {
        return vehicleFrontPhotoURL;
    }

    public String getVehicleBackPhotoURL() {
        return vehicleBackPhotoURL;
    }

    public BigDecimal getVehicleWeight() {
        return vehicleWeight;
    }

    public BigDecimal getVehicleLength() {
        return vehicleLength;
    }

    public BigDecimal getVehicleWidth() {
        return vehicleWidth;
    }

    public BigDecimal getVehicleHeight() {
        return vehicleHeight;
    }

    public User getDriver() {
        return driver;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
    }

    public void setVehicleFrontPhotoURL(String vehicleFrontPhotoURL) {
        this.vehicleFrontPhotoURL = vehicleFrontPhotoURL;
    }

    public void setVehicleBackPhotoURL(String vehicleBackPhotoURL) {
        this.vehicleBackPhotoURL = vehicleBackPhotoURL;
    }

    public void setVehicleWeight(BigDecimal vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public void setVehicleLength(BigDecimal vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public void setVehicleWidth(BigDecimal vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public void setVehicleHeight(BigDecimal vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

}
