package com.softserve.edu.delivery.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import static com.softserve.edu.delivery.dto.valid.PatternConstraints.*;

public class DriverRegistrationDTO {

    @NotNull(message = EMAIL_NOT_VALID_MESSAGE)
    @Email
    private String driverEmail;

    @NotNull(message = NAME_NOT_VALID_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String driverFirstName;

    @NotNull(message = NAME_NOT_VALID_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String driverLastName;

    @NotNull(message = PASS_NOT_VALID_MESSAGE)
    @Size(min = PASS_MIN_LENGTH, max = PASS_MAX_LENGTH)
    private String driverPassword;

    @NotNull(message = PASS_NOT_VALID_MESSAGE)
    @Size(min = PASS_MIN_LENGTH, max = PASS_MAX_LENGTH)
    private String driverConfirmPassword;

    @NotNull(message = PHONE_NOT_VALID_MASSAGE)
    @Pattern(regexp = PHONE_REGEX)
    private String driverPhoneNumber;

    private String driverPassport;

    private String driverPhotoUrl;

    @NotNull(message = NOT_NULL_VALID_MASSAGE)
    @Size(min = DEFAULT_MIN_LENGTH, max = DEFAULT_MAX_LENGTH)
    private String vehicleName;

    @NotNull(message = NOT_NULL_VALID_MASSAGE)
    @Size(min = DEFAULT_MIN_LENGTH, max = DEFAULT_MAX_LENGTH)
    private String vehicleNumber;

    @NotNull(message = CAR_VIN_NOT_VALID_MESSAGE)
    @Size(min = 1, max = CAR_VIN_LENGTH)
    private String vehicleVIN;

    private String vehicleFrontPhotoURL;

    private String vehicleBackPhotoURL;

    private BigDecimal vehicleWeight;

    private BigDecimal vehicleLength;

    private BigDecimal vehicleWidth;

    private BigDecimal vehicleHeight;


    public String getDriverEmail() {
        return driverEmail;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public String getDriverConfirmPassword() {
        return driverConfirmPassword;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getDriverPassport() {
        return driverPassport;
    }

    public String getDriverPhotoUrl() {
        return driverPhotoUrl;
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

    public DriverRegistrationDTO setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
        return this;
    }

    public DriverRegistrationDTO setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
        return this;
    }

    public DriverRegistrationDTO setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
        return this;
    }

    public DriverRegistrationDTO setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
        return this;
    }

    public DriverRegistrationDTO setDriverConfirmPassword(String driverConfirmPassword) {
        this.driverConfirmPassword = driverConfirmPassword;
        return this;
    }

    public DriverRegistrationDTO setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
        return this;
    }

    public DriverRegistrationDTO setDriverPassport(String driverPassport) {
        this.driverPassport = driverPassport;
        return this;
    }

    public DriverRegistrationDTO setDriverPhotoUrl(String driverPhotoUrl) {
        this.driverPhotoUrl = driverPhotoUrl;
        return this;
    }

    public DriverRegistrationDTO setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
        return this;
    }

    public DriverRegistrationDTO setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public DriverRegistrationDTO setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
        return this;
    }

    public DriverRegistrationDTO setVehicleFrontPhotoURL(String vehicleFrontPhotoURL) {
        this.vehicleFrontPhotoURL = vehicleFrontPhotoURL;
        return this;
    }

    public DriverRegistrationDTO setVehicleBackPhotoURL(String vehicleBackPhotoURL) {
        this.vehicleBackPhotoURL = vehicleBackPhotoURL;
        return this;
    }

    public DriverRegistrationDTO setVehicleWeight(BigDecimal vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
        return this;
    }

    public DriverRegistrationDTO setVehicleLength(BigDecimal vehicleLength) {
        this.vehicleLength = vehicleLength;
        return this;
    }

    public DriverRegistrationDTO setVehicleWidth(BigDecimal vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
        return this;
    }

    public DriverRegistrationDTO setVehicleHeight(BigDecimal vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
        return this;
    }
}
