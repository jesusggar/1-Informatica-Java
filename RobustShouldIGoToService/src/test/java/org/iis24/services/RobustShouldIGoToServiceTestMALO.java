package org.iis24.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RobustShouldIGoToServiceTestMALO {
    RobustShouldIGoToService service;
    LinkedList<IWeatherService> ws;
    LinkedList<IMapService> ms;
    IWeatherService wsE = mock(IWeatherService.class);
    IMapService msE = mock(IMapService.class);

    @BeforeEach
    void setUp() {
        ws = new LinkedList<>();
        ms = new LinkedList<>();
        ws.add(wsE);
        ms.add(msE);
        service = new RobustShouldIGoToService(ms, ws);
    }

    @AfterEach
    void tearDown() {
        ws = null;
        ms = null;
        service = null;
    }

    @Test
    void shouldRaiseInvalidServiceInstanceExceptionWhenListsContainNullReferences() {
        ws.add(null);
        ms.add(null);
        assertThrows(InvalidServiceInstanceException.class, ()-> service = new RobustShouldIGoToService(ms, ws));
    }

    @Test
    void shouldRaiseInvalidMapServiceExecutionExceptionIfMapServiceFails() {
        when(msE.getCoordinates(null)).thenThrow(new InvalidMapServiceExecutionException("Sin coords"));
        ms.add(msE);
        assertThrows(InvalidMapServiceExecutionException.class,
                ()-> service.shouldIGoTo(null, LocalDate.parse("2024-05-09")));
    }

    @Test
    void shouldInvokeWeatherServiceWithValidCoordinatesIfOneMapServiceWorksWell() {
        GPSCoordinates correctGPSCoords = new GPSCoordinates(50.0, 40.0);
        when(msE.getCoordinates("Place")).thenReturn(correctGPSCoords);
        ms.add(msE);
        verify(service.weatherServices.getLast()).totalAmountOfRain(correctGPSCoords, LocalDate.now());
        verify(service.weatherServices.getLast()).rainProbability(correctGPSCoords, LocalDate.now());
    }
}
