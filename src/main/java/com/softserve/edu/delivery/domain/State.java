package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Shtenovych
 * */
@Entity
@Table(name = "STATES")
@Access(AccessType.PROPERTY)
public class State implements Serializable, Comparable<State> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "state_name")
    private String stateName;

    @OneToMany
    @JoinColumn(name = "region_id")
    private List<Region> regions;

    public State(String stateName, List<Region> regions) {
        this.stateName = stateName;
        this.regions = regions;
    }

    public State(String stateName) {
        this(stateName, new ArrayList<>());
    }

    public State() {
        this("");
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public int hashCode() {
        return this.stateName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) return false;
        else {
            return this.stateName.equalsIgnoreCase(((State)obj).getStateName());
        }
    }

    @Override
    public int compareTo(State that) {
        return this.stateName.compareTo(that.getStateName());
    }
}