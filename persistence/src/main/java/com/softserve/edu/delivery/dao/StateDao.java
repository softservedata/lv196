package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.State;
import java.util.List;

public interface StateDao extends BaseDao<State, Long> {
    /**
     * Finds all region in state
     * @return list of all state in system
     */
    List<State> getAllState();
}
