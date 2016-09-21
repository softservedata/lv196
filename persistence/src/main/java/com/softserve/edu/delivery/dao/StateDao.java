package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.State;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public interface StateDao extends BaseDao<State, Long> {
    List<State> getAllState();
}
