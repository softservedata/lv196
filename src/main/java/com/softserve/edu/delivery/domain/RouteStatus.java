package com.softserve.edu.delivery.domain;
/**
 * Author - Ivan Synyshyn
 */
public enum RouteStatus {
	OPEN("Open"),
	IN_ROUTE("In route"),
	CLOSED("Closed"),
	CANCELLED("Cancelled");
	
	private String routeStatus;
	
	RouteStatus(String status){
		routeStatus = status;
	}

	public String getRouteStatus() {
		return routeStatus;
	}
}
