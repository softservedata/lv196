package com.softserve.edu.delivery.dto;

public class CarDTO {

    //need to add field from document
    private String vehicleVIN;
    private Double vehicleWeight;
    private Double vehicleLength;
    private Double vehicleWidth;
    private Double vehicleHeight;
    
    public CarDTO() {
    }

    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public Double getVehicleWeight() {
        return vehicleWeight;
    }

    public Double getVehicleLenght() {
        return vehicleLength;
    }

    public Double getVehicleWidth() {
        return vehicleWidth;
    }

    public Double getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
    }

    public void setVehicleWeight(Double vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public void setVehicleWidth(Double vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public void setVehicleHeight(Double vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }
    
}
