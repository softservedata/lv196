package com.softserve.edu.delivery.dto;

public class CarDTO {

    private Long id;
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleVIN;
    private String vehicleFrontPhotoURL;
    private String vehicleBackPhotoURL;
    private Float vehicleWeight;
    private Float vehicleLength;
    private Float vehicleWidth;
    private Float vehicleHeight;
    private String driverEmail;
    private float progress;
    private boolean showProgressFront;
    private boolean showProgressBack;
    
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

    public Float getVehicleWeight() {
        return vehicleWeight;
    }

    public Float getVehicleLength() {
        return vehicleLength;
    }

    public Float getVehicleWidth() {
        return vehicleWidth;
    }

    public Float getVehicleHeight() {
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

    public void setVehicleWeight(Float vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public void setVehicleLength(Float vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public void setVehicleWidth(Float vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public void setVehicleHeight(Float vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public boolean isShowProgressFront() {
        return showProgressFront;
    }

    public void setShowProgressFront(boolean showProgressFront) {
        this.showProgressFront = showProgressFront;
    }

    public boolean isShowProgressBack() {
        return showProgressBack;
    }

    public void setShowProgressBack(boolean showProgressBack) {
        this.showProgressBack = showProgressBack;
    }
}
