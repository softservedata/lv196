package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
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
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City cityFrom;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City cityTo;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus;

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
	
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public City getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(City cityFrom) {
		this.cityFrom = cityFrom;
	}

	public City getCityTo() {
		return cityTo;
	}

	public void setCityTo(City cityTo) {
		this.cityTo = cityTo;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id)&&
                Objects.equals(weight, order.weight);
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
