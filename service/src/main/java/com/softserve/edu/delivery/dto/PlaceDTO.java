package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;

/**
 * Created by Natalia on 02.10.2016.
 */
public class PlaceDTO {
    private Long id;
    //private Order order;
    private Point point;
    private String date;

    public PlaceDTO(){}

    public PlaceDTO(Point point, String date/*, Order order*/) {
        this.point = point;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Point getPoint() {
        return point;
    }

   public static PlaceDTO convertEntity(RouteCities routeCities){
        return new PlaceDTO(new Point(routeCities.getLatitude(), routeCities.getLongitude()),
                routeCities.getVisitDate().toString());
    }

    @Override
    public String toString() {
        return "PleaceDto{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
