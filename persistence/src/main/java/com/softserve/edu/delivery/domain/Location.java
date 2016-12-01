package com.softserve.edu.delivery.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    private String id;
    private String formatted;
    private String city;
    private String region;
    private String oblast;
    private Double latitude;
    private Double longitude;
    private boolean custom;

    public Location() {
    }

    public String getId() {
        return id;
    }

    public Location setId(String id) {
        this.id = id;
        return this;
    }

    public String getFormatted() {
        return formatted;
    }

    public Location setFormatted(String formatted) {
        this.formatted = formatted;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Location setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Location setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getOblast() {
        return oblast;
    }

    public Location setOblast(String oblast) {
        this.oblast = oblast;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Location setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Location setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public boolean isCustom() {
        return custom;
    }

    public Location setCustom(boolean custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
