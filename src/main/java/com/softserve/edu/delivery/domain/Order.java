package com.softserve.edu.delivery.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "width")
	private Double width;
	
	@Column(name = "length")
	private Double length;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "arrival_date")
	private Date arrivalDate;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User idUser;
	
	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route idRoute;
	
	@ManyToOne
	@JoinColumn(name = "order_status_id")
	private OrderStatus idOredrStatus;

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Route getIdRoute() {
		return idRoute;
	}

	public void setIdRoute(Route idRoute) {
		this.idRoute = idRoute;
	}

	public OrderStatus getIdOredrStatus() {
		return idOredrStatus;
	}

	public void setIdOredrStatus(OrderStatus idOredrStatus) {
		this.idOredrStatus = idOredrStatus;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", height=" + height + ", width=" + width +
				", length=" + length + ", weight=" + weight + ", price=" + price + 
				", registrationDate=" + registrationDate + ", arrivalDate="	+ arrivalDate +
				", description=" + description + "]";
	}
	
}
