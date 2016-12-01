package com.softserve.edu.delivery.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class OrderFilterDto {

    private Long id;
    private String cityFromId;
    private String cityToId;
    private Double weight;
    private Timestamp arrivalDate;
    private Integer currentPage;
    private Integer itemsPerPage;

    public OrderFilterDto() {
    }

    public OrderFilterDto(Long id, String cityFromId, String cityToId, Double weight, Timestamp arrivalDate, Integer currentPage, Integer itemsPerPage) {

        this.id = id;
        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(String cityFromId) {
        this.cityFromId = cityFromId;
    }

    public String getCityToId() {
        return cityToId;
    }

    public void setCityToId(String cityToId) {
        this.cityToId = cityToId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public String toString() {
        return "OrderFilterDto{" +
                "cityFromId=" + cityFromId +
                ", cityToId=" + cityToId +
                ", weight=" + weight +
                ", arrivalDate=" + arrivalDate +
                ", currentPage=" + currentPage +
                ", itemsPerPage=" + itemsPerPage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFilterDto orderFilterDto = (OrderFilterDto) o;
        return Objects.equals(id, orderFilterDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
