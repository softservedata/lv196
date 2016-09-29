package com.softserve.edu.delivery.service.pleases;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.dao.StateDao;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;
import com.softserve.edu.delivery.dto.RegionDto;
import com.softserve.edu.delivery.dto.StateDto;
import com.softserve.edu.delivery.service.impl.TransporterServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackingServiceImplTest {
    @Mock
    CityDao cityDao;
    @Mock
    RegionDao regionDao;
    @Mock
    StateDao stateDao;
    @InjectMocks
    TransporterServiceImpl transporterService;
    @Spy
    List<City> cities = new ArrayList<>();
    @Spy
    List<Region> regions = new ArrayList<>();
    @Spy
    List<State> states = new ArrayList<>();
    @Spy
    List<RegionDto> regionsDto = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        states = getState();
        regions = getRegion();
        cities = getCity();

    }
    @Test
    public void getALLState(){
        when(stateDao.getAllState()).thenReturn(states);
        Assert.assertEquals(transporterService
                .getAllState().stream()
                .map(dto -> TransporterServiceImpl.convertToEntity(dto))
                .collect(Collectors.toList()), states);
        verify(stateDao, times(1)).getAllState();
    }
    @Test
    public void getRegionByState(){
        String state = "Lviv state";
        when(regionDao.getRegionByState(state)).thenReturn(regions);
        Assert.assertEquals(transporterService
                .getRegionByState(state).stream()
                .map(dto -> TransporterServiceImpl.convertToEntity(dto))
                .collect(Collectors.toList()), regions);
        verify(regionDao, times(1)).getRegionByState(state);
    }


    @Test
    public void getCityByRegion(){
        String region = "Lviv state";
        when(cityDao.getCityByRegion(region)).thenReturn(cities);
        Assert.assertEquals(transporterService
                .getCityByRegion(region).stream()
                .map(dto -> TransporterServiceImpl.convertToEntity(dto))
                .collect(Collectors.toList()), cities);
        verify(cityDao, times(1)).getCityByRegion(region);
    }
    private List<State> getState(){
        List<State> result = new ArrayList<>();
        result.add(new State("mmmmm"));
        result.add(new State("Lviv state"));
        return result;
    }
    private List<Region> getRegion(){
        List<Region> result = new ArrayList<>();
        result.add(new Region("Brodu region", new State("Lviv state")));
        result.add(new Region("Striy region", new State("Lviv state")));
        result.add(new Region("Berejany region", new State("Ternopil state")));
        return result;
    }
    private List<City> getCity(){
        List<City> result = new ArrayList<>();
        result.add(new City(1L,"Brodu",new Region("Brodu region", new State("Lviv state"))));
        result.add(new City(2L, "Striy",new Region("Striy region", new State("Lviv state"))));
        return result;
    }


}