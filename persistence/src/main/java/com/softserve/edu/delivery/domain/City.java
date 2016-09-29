package com.softserve.edu.delivery.domain;
/**
 * Author - Ivan Synyshyn
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CITIES")
public class City {

	private Long cityId;
	private String cityName;
	private Region region;

	public City() {
	}

	public City(String cityName, Region region) {
		this.cityName = cityName;
		this.region = region;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}

	public City setCityId(Long cityId) {
		this.cityId = cityId;
		return this;
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
		if (this == obj)
			return true;
		if ((obj == null) || this.getClass() != obj.getClass())
			return false;
		City city = (City) obj;
		return Objects.equals(cityId, city.cityId) && Objects.equals(cityName, city.cityName);
	}

	@Override
	public int hashCode() {
		return (int) (17 * cityId + 37 * cityName.length());
	}

	@Override
	public String toString() {
		return "City [id = " + cityId + ", City = " + cityName + ", Region = " + region + "]";
	}

}
