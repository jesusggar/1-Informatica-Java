package org.iis24.services;

import org.iis24.services.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RobustShouldIGoToServiceTest {
    private final int MAX_WS = 5;
    private final int MAX_MS = 5;

    //variables needed for the tests:
    LinkedList<IWeatherService> ws;
    LinkedList<IMapService> ms;
    ShouldIGoToService service;

    @BeforeEach
    void setUp(){
        ws = new LinkedList<IWeatherService>();
        ms = new LinkedList<>();
        for (int i = 1; i <= MAX_WS; i++){
            ws.add(mock(IWeatherService.class));
        }
        for (int i = 1; i <= MAX_MS; i++){
            ms.add(mock(IMapService.class));
        }
        service = new RobustShouldIGoToService(ms, ws);
    }

    @AfterEach
    void tearDown(){
        service = null;
        ws = null;
        ms = null;
    }

    @Test
    void shouldRaiseInvalidServiceInstanceExceptionWhenListsContainNullReferences(){
        //Arrange
        ms.set(3, null);

        //Act

        //Assert
        assertThrows(InvalidServiceInstanceException.class,
                () -> service = new RobustShouldIGoToService(ms, ws) ) ;
    }

    @Test
    void shouldRaiseInvalidMapServiceExecutionExceptionIfMapServiceFails(){
        //Arrange
        ms.set(1, null);
        //Act

        //Assert
        assertThrows(InvalidMapServiceExecutionException.class,
                ()-> service.shouldIGoTo("Nerja", LocalDate.now()));
    }

    @Test
    void shouldInvokeWeatherServiceWithValidCoordinatesIfOneMapServiceWorksWell(){
        //Arrange
        GPSCoordinates gpsCoordinates = new GPSCoordinates(10.0, 10.0);
        when(ms.getLast().getCoordinates("Nerja")).thenReturn(gpsCoordinates);
        service.setRainAmountThreshold(50);
        service.setRainProbabilityThreshold(50);
        LocalDate today = LocalDate.now();
        //Act
        service.shouldIGoTo("Nerja", today); //(el ver si algo se ha invocado se hace con mokito)
        //Assert
        verify(ws.getFirst(), times(1)).rainProbability(gpsCoordinates, today);
        verify(ws.getFirst(), times(1)).totalAmountOfRain(gpsCoordinates, today);
    }

    @Test
    void shouldReturnTrueWhenAverageOfProbabilitiesAndTotalAmountOfRainAreZero(){
        //Arrange
        GPSCoordinates GPSCoords = new GPSCoordinates(10, 10);
        when(ms.getLast().getCoordinates("Nerja")).thenReturn(GPSCoords);
        LocalDate localDate = LocalDate.now();

        for (int i = 0; i < MAX_WS; ++i){
            when(ws.get(i).rainProbability(GPSCoords, localDate)).thenReturn(0.0);
            when(ws.get(i).totalAmountOfRain(GPSCoords, localDate)).thenReturn(0.0);
        }
        service.setRainProbabilityThreshold(20);
        service.setRainAmountThreshold(20);

        //Act
        boolean result = service.shouldIGoTo("Nerja", localDate);
        //Assert
        assertTrue(result, "Something went wrong with the prediction");
    }

    @Test
    void shouldReturnFalseWhenAverageOfProbabilitiesAndTotalAmountOfRainAreEqualToThresholds(){
        //Arrange
        GPSCoordinates GPSCoords = new GPSCoordinates(10, 10);
        when(ms.getLast().getCoordinates("Nerja")).thenReturn(GPSCoords);
        LocalDate localDate = LocalDate.now();

        for (int i = 0; i < MAX_WS; ++i){
            when(ws.get(i).rainProbability(GPSCoords, localDate)).thenReturn(20.0);
            when(ws.get(i).totalAmountOfRain(GPSCoords, localDate)).thenReturn(20.0);
        }
        service.setRainProbabilityThreshold(20);
        service.setRainAmountThreshold(20);

        //Act
        boolean result = service.shouldIGoTo("Nerja", localDate);
        //Assert
        assertFalse(result, "Something went wrong with the prediction");
    }

    @Test
    void shouldInvokeAllAvailableWeatherServices(){
        //Arrange
        GPSCoordinates GPSCoords = new GPSCoordinates(10, 10);
        when(ms.getLast().getCoordinates("Nerja")).thenReturn(GPSCoords);
        LocalDate localDate = LocalDate.now();

        for (int i = 0; i < MAX_WS; ++i){
            when(ws.get(i).rainProbability(GPSCoords, localDate)).thenReturn(20.0);
            when(ws.get(i).totalAmountOfRain(GPSCoords, localDate)).thenReturn(20.0);
        }
        service.setRainProbabilityThreshold(20);
        service.setRainAmountThreshold(20);
        //Act
        boolean result = service.shouldIGoTo("Nerja", localDate); //(no se utiliza)
        //Assert
        for (int i = 0; i < MAX_WS; i++){
            verify(ws.get(i)).rainProbability(GPSCoords, localDate);
        }
    }
}
