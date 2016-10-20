package com.softserve.edu.delivery.service;

import java.util.List;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.dto.CityDto;
import com.softserve.edu.delivery.dto.PleaceDto;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;

public interface TransporterService {
    List<PleaceDto> getAllPleaces();
}
