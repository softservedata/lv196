package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.StateDao;
import com.softserve.edu.delivery.domain.State;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class StateDaoImpl extends BaseDaoImpl<State, Long> implements StateDao {
    public StateDaoImpl() {
        super(State.class);
    }
}
