package com.softserve.edu.delivery.domain;

/**
 * Created by Natalia on 12.10.2016.
 */
public class Point {
    private Double lat;
    private Double lng;
    public Point(){}

    public Double getX() {
        return lat;
    }

    public void setX(Double x) {
        this.lat = x;
    }

    public Double getY() {
        return lng;
    }

    public void setY(Double y) {
        this.lng = y;
    }

    public Point(Double x, Double y) {
        this.lat = x;
        this.lng = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + lat +
                ", y=" + lng +
                '}';
    }
}
