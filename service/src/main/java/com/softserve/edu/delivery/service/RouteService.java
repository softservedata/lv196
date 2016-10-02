package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.dto.RouteDTO;

import java.sql.Timestamp;

/**
 * @author Petro Shtenovych
 * */

public interface RouteService {

    RouteDTO getRouteById(Long id);
    void savePleace(PleaceDto pleaceDto);
}
