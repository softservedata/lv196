package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal weight;
    private BigDecimal price;
    private Timestamp registrationDate;
    private Timestamp arrivalDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "city_from_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to_id")
    private City cityTo;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Offer> offers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public Order setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public Order setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public BigDecimal getLength() {
        return length;
    }

    public Order setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Order setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public Order setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public Order setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getCustomer() {
        return customer;
    }

    public Order setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public Order setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
        return this;
    }

    public City getCityTo() {
        return cityTo;
    }

    public Order setCityTo(City cityTo) {
        this.cityTo = cityTo;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public Order setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
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
                ", registrationDate=" + registrationDate + ", arrivalDate=" + arrivalDate +
                ", description=" + description + "]";
    }

}
