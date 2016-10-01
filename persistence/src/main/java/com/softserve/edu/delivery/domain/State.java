package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STATES")
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "state_name")
    private String stateName;

    @OneToMany(mappedBy = "state")
    private List<Region> regions = new ArrayList<>();

    public State(String stateName, List<Region> regions) {
        this.stateName = stateName;
        this.regions = regions;
    }

    public State(String stateName) {
        this(stateName, new ArrayList<>());
    }

    public State() {
    }

    public Long getStateId() {
        return stateId;
    }

    public State setStateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public State setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public State setRegions(List<Region> regions) {
        this.regions = regions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(stateId, state.stateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId);
    }
}