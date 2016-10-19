package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.service.LocationService;
import com.softserve.edu.delivery.service.TransporterService;

/**
 * Created by Natalia on 02.10.2016.
 */
public class PleaceDto {
    private Long id;
    //private Order order;
    private Point point;
    private String date;

    public PleaceDto(){}

    public PleaceDto(Point point, String date/*, Order order*/) {
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

   public static PleaceDto convertEntity(RouteCities routeCities){
        return new PleaceDto(new Point(routeCities.getX(), routeCities.getY()), routeCities.getVisitDate().toString());
    }

    @Override
    public String toString() {
        return "PleaceDto{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
