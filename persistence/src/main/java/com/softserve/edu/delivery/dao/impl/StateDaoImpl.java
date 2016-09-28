package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.StateDao;
import com.softserve.edu.delivery.domain.State;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
@Repository("stateDao")
public class StateDaoImpl extends BaseDaoImpl<State, Long> implements StateDao {
    public StateDaoImpl() {
        super(State.class);
    }
    @Override
    public List<State> getAllState() {
        return getEntityManager()
                .createQuery("select s from State s", State.class)
                .getResultList();
    }
}
