package com.softserve.edu.delivery.dto;

public class CarDTO {

    private String vehicleName;
    private String vehicleNumber;
    private String vehicleVIN;
    private String vehicleFrontPhotoURL;
    private String vehicleBackPhotoURL;
    private Double vehicleWeight;
    private Double vehicleLength;
    private Double vehicleWidth;
    private Double vehicleHeight;
    
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

    public Double getVehicleWeight() {
        return vehicleWeight;
    }

    public Double getVehicleLength() {
        return vehicleLength;
    }

    public Double getVehicleWidth() {
        return vehicleWidth;
    }

    public Double getVehicleHeight() {
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

    public void setVehicleWeight(Double vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public void setVehicleLength(Double vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public void setVehicleWidth(Double vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public void setVehicleHeight(Double vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }
    
    
}
