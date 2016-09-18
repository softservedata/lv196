package com.softserve.edu.delivery.dto;

/**
 * Created by Natalia on 18.09.2016.
 */
public class StateDto {
    private Long stateId;
    private String name;

    public StateDto(Long stateId,String name) {
        this.stateId = stateId;
        this.name = name;
    }
    public StateDto(){}

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
