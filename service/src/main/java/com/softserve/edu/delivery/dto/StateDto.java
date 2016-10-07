package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.State;
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

    public static StateDto convertEntity(State state){
        return new StateDto(state.getStateId(), state.getStateName());
    }

    @Override
    public String toString() {
        return "StateDto{" +
                "stateId=" + stateId +
                ", name='" + name + '\'' +
                '}';
    }
}
