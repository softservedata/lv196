package com.softserve.edu.delivery.dto;



import com.softserve.edu.delivery.domain.Point;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Natalia on 21.10.2016.
 */
public class RoutesDto {
    Point from;
    Point to;
    PlaceDto currentLocation;

    public RoutesDto(Point from, Point to, PlaceDto currentLocation) {
        this.from = from;
        this.to = to;
        this.currentLocation = currentLocation;
    }

    public Point getIdFrom() {
        return from;
    }

    public void setIdFrom(Point idFrom) {
        this.from = idFrom;
    }

    public Point getIdTo() {
        return to;
    }

    public void setIdTo(Point idTo) {
        this.to = idTo;
    }

    public PlaceDto getPoints() {
        return currentLocation;
    }

    public void setPoints(PlaceDto currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "RoutesDto{" +
                "idFrom=" + from +
                ", idTo=" + to +
                ", points=" + currentLocation +
                '}';
    }
}
