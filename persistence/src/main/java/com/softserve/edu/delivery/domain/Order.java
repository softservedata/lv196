package com.softserve.edu.delivery.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "height")
    private BigDecimal height;

    @Column(name = "width")
    private BigDecimal width;

    @Column(name = "length")
    private BigDecimal length;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @Column(name = "arrival_date")
    private Timestamp arrivalDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "city_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "city_id")
    private City cityTo;

    @OneToMany
    @JoinColumn(name = "offer_id")
    private List<Offer> offers;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
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
