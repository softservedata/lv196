package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Location;
import com.softserve.edu.delivery.repository.LocationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Before
    public void before()  {
        when(locationRepository.save(any(Location.class)))
                .thenAnswer(invocation -> invocation.getArgumentAt(0, Location.class));
    }

    @Test
    public void persistCustomLocation() throws Exception {
        Location customLoc = new Location().setCustom(true)
                .setFormatted("Formatted custom");

        Location actual = locationService.persistLocation(customLoc);

        // throws exception if id is not UUID
        UUID.fromString(actual.getId());

        customLoc.setId(actual.getId());
        Assert.assertEquals(customLoc, actual);
    }


    @Test
    public void addNonCustomLocation() {
        Location location = new Location().setId("123")
                .setFormatted("Formatted name");

        Location actual = locationService.persistLocation(location);

        Assert.assertEquals(location, actual);
    }

}
