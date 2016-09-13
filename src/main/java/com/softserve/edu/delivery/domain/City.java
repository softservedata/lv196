package com.softserve.edu.delivery.domain;
/**
 * Author - Ivan Synyshyn
 */
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
@Table(name = "CITIES")
public class City implements Comparable<City>{

	private Long cityId;
	private String cityName;
	private Region region;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@ManyToOne
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public City(Long cityId, String cityName, Region region) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.region = region;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if ((obj == null) || this.getClass() != obj.getClass()) return false;
		City city = (City) obj;
		return Objects.equals(cityName, city.cityName) && Objects.equals(region, city.region);
	}

	@Override
	public int hashCode() {
		return (int) (17 * cityId + 37 * cityName.length());
	}
	
	@Override
	public String toString() {
		return "City [id = " + cityId + ", City = " + cityName + ", Region = " + region + "]";
	}

	@Override
	public int compareTo(City city) {
		return cityName.compareTo(city.cityName);
	}
}
