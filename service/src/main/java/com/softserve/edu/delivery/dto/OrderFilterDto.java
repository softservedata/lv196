package com.softserve.edu.delivery.dto;

import java.sql.Timestamp;

/**
 * Created by Ivan on 18.11.2016.
 */
public class OrderFilterDto {
    private Long cityFromId;
    private Long cityToId;
    private Double weight;
    private Timestamp arrivalDate;
    private Integer currentPage;
    private Integer itemsPerPage;

    public OrderFilterDto() {
    }

    public OrderFilterDto(Long cityFromId, Long cityToId, Double weight, Timestamp arrivalDate, Integer currentPage, Integer itemsPerPage) {

        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public Long getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(Long cityFromId) {
        this.cityFromId = cityFromId;
    }

    public Long getCityToId() {
        return cityToId;
    }

    public void setCityToId(Long cityToId) {
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

        OrderFilterDto that = (OrderFilterDto) o;

        if (cityFromId != null ? !cityFromId.equals(that.cityFromId) : that.cityFromId != null) return false;
        if (cityToId != null ? !cityToId.equals(that.cityToId) : that.cityToId != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return arrivalDate != null ? arrivalDate.equals(that.arrivalDate) : that.arrivalDate == null;

    }

    @Override
    public int hashCode() {
        int result = cityFromId != null ? cityFromId.hashCode() : 0;
        result = 31 * result + (cityToId != null ? cityToId.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        return result;
    }
}
