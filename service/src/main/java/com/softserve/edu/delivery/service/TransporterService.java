package com.softserve.edu.delivery.service;

import java.util.List;

import com.softserve.edu.delivery.dto.*;
import org.springframework.data.domain.Pageable;

public interface TransporterService {
    List<PlaceDto> getAllPleaces();
    List<PlaceDto> getAllPleacesByOrderId(Long id);
    List<RoutesDto> getAllOrdersInProgress(Pageable pageable);
    Long getCount();
}
