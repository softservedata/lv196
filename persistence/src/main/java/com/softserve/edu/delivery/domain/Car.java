package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleVIN;
    private String vehicleFrontPhotoURL;
    private String vehicleBackPhotoURL;
    @Column(columnDefinition="Float(10,2)")
    private Float vehicleWeight;
    @Column(columnDefinition="Float(10,2)")
    private Float vehicleLength;
    @Column(columnDefinition="Float(10,2)")
    private Float vehicleWidth;
    @Column(columnDefinition="Float(10,2)")
    private Float vehicleHeight;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "email")
    private User driver;

    @OneToMany(mappedBy = "car")
    private List<Offer> offers = new ArrayList<>();

    @Column(columnDefinition="boolean default true")
    private Boolean active;

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

    public Float getVehicleWeight() {
        return vehicleWeight;
    }

    public Car setVehicleWeight(Float vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
        return this;
    }

    public Float getVehicleLength() {
        return vehicleLength;
    }

    public Car setVehicleLength(Float vehicleLength) {
        this.vehicleLength = vehicleLength;
        return this;
    }

    public Float getVehicleWidth() {
        return vehicleWidth;
    }

    public Car setVehicleWidth(Float vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
        return this;
    }

    public Float getVehicleHeight() {
        return vehicleHeight;
    }

    public Car setVehicleHeight(Float vehicleHeight) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
