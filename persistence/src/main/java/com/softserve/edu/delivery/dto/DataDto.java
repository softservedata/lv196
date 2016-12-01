package com.softserve.edu.delivery.dto;

import java.util.Objects;

public class DataDto {
	
	private String itemType;
	private Long count;
	
    public DataDto() {
	}

	public DataDto(String itemType, Long count) {
		this.itemType = itemType;
		this.count = count;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDto data = (DataDto) o;
        return Objects.equals(itemType, data.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType);
    }

}
