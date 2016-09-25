package com.softserve.edu.delivery.dto;

import java.math.BigDecimal;

public class CarDTO {

    private String vehicleName;
    private String vehicleNumber;
    private String vehicleVIN;
    private String vehicleFrontPhotoURL;
    private String vehicleBackPhotoURL;
    private BigDecimal vehicleWeight;
    private BigDecimal vehicleLength;
    private BigDecimal vehicleWidth;
    private BigDecimal vehicleHeight;
    
    public CarDTO() {
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

    
}
