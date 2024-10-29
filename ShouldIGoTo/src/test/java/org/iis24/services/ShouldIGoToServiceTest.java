package org.iis24.services;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShouldIGoToServiceTest {

    ShouldIGoToService service;
    IMapService ms;
    IWeatherService ws;

    @BeforeEach
    void setUp() {
        ms = mock(IMapService.class);
        ws = mock(IWeatherService.class);
        service = new ShouldIGoToService(ms, ws);
    }

    @AfterEach
    void tearDown() {
        ms = null;
        ws = null;
        service = null;
    }

    @Test
    void shouldRaiseInvalidServiceInstanceExceptionWhenAnyOfTheServiceInstancesProvidedServiceInstanceIsNotValid() {
        assertAll("Check all cases...",
                ()-> assertThrows(InvalidServiceInstanceException.class, ()-> service = new ShouldIGoToService(null, null)),
                ()-> assertThrows(InvalidServiceInstanceException.class, ()-> service = new ShouldIGoToService(ms, null)),
                ()-> assertThrows(InvalidServiceInstanceException.class, ()-> service = new ShouldIGoToService(null, ws))
                );
    }

    @Test
    void ShouldRaiseInvalidRainAmountExceptionWhenProvidedRainInAmountIsNotValid() {
        assertThrows(InvalidRainAmountException.class, ()-> service.setRainAmountThreshold(-0.01));
    }

    @Test
    void shouldRaiseInvalidMapServiceExecutionExceptionWhenMapServiceExecutionFails() {
        when(ms.getCoordinates(null)).thenThrow(new RuntimeException());

        RuntimeException e = assertThrows(RuntimeException.class,
                ()-> service.shouldIGoTo(null, LocalDate.parse("2026-01-01")));
    }

    @Test
    void ShouldInvokeWeatherServiceWithCorrectGPSCoordinatesWhenLegalPlaceDescription() {
        GPSCoordinates correctGPSCoords = new GPSCoordinates(36.0, 40.0);
        when(ms.getCoordinates("Nerja")).thenReturn(correctGPSCoords);

        LocalDate today = LocalDate.now();

        service.shouldIGoTo("Nerja", today);
        verify(ws).rainProbability(correctGPSCoords, today);
        verify(ws).totalAmountOfRain(correctGPSCoords, today);
    }

    @Test
    void shouldReturnTrueWhenRainProbabilyIsZero() {
        when(ws.rainProbability(any(), any())).thenReturn(0.0);
        boolean result = service.shouldIGoTo("Nerja", LocalDate.now());
        assertEquals(true, result);
    }

    @Test
    void shouldReturnFalseWhenRainProbabilityAndAmountAreEqualToThresholds() {
        GPSCoordinates correctGPSCoords = new GPSCoordinates(38, 40);
        when(ms.getCoordinates("Nerja")).thenReturn(correctGPSCoords);
        when(ws.rainProbability(any(), any())).thenReturn(50.0);
        when(ws.totalAmountOfRain(any(), any())).thenReturn(50.0);

        service.setRainProbabilityThreshold(50.0);
        service.setRainAmountThreshold(50.0);
        LocalDate today = LocalDate.now();

        boolean result = service.shouldIGoTo("Nerja", LocalDate.now());

        verify(ws, times(1)).rainProbability(correctGPSCoords, today);
        verify(ws, times(1)).totalAmountOfRain(correctGPSCoords, today);

        assertEquals(false, result);
    }
}
