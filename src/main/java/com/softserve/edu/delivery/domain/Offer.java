package com.softserve.edu.delivery.domain;
/**
 * Author - Ivan Synyshyn
 */
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Routes")
public class Offer {
	
	private long routeId;
	private City cityFrom;
	private City cityTo;
	private Car car;
	private Date startTime;
	private Date finishTime;
	private RouteStatus status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "route_id")
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	
	@ManyToOne
	@JoinColumn(name = "from_id", referencedColumnName = "city_id")
	public City getCityFrom() {
		return cityFrom;
	}
	public void setCityFrom(City cityFrom) {
		this.cityFrom = cityFrom;
	}
	
	@ManyToOne
	@JoinColumn(name = "to_id", referencedColumnName = "city_id")
	public City getCityTo() {
		return cityTo;
	}
	public void setCityTo(City cityTo) {
		this.cityTo = cityTo;
	}
	
	@ManyToOne
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "finish_time")
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "route_status")
	public RouteStatus getStatus() {
		return status;
	}
	public void setStatus(RouteStatus status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if ((obj == null) || this.getClass() != obj.getClass()) return false;
		Offer route = (Offer) obj;
		return Objects.equals(routeId, route.routeId);
	}

	@Override
	public int hashCode() {
		return (int) (37 * routeId);
	}
	
	@Override
	public String toString() {
		return "Route [id = " + routeId + ", route = " + cityFrom + " - " + cityTo + 
				", time limit = " + startTime + " - " + finishTime + ", car = " + car + ", route is: " + status + "]";
	}
}
