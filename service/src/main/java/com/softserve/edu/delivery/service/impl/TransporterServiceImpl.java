package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dto.PlaceDTO;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import com.softserve.edu.delivery.service.TransporterService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransporterServiceImpl implements TransporterService {
    private final RouteCitiesRepository routeCityRepository;

    @Autowired
    public TransporterServiceImpl(RouteCitiesRepository routeCityRepository) {
        this.routeCityRepository = routeCityRepository;
    }
    public List<PlaceDTO> getAllPleaces() {
        return routeCityRepository.findAll()
                .stream()
                .map(entity -> PlaceDTO.convertEntity(entity))
                .collect(Collectors.toList());
    }

}
