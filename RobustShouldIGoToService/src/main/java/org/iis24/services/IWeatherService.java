package org.iis24.services;

import java.time.LocalDate;

/**
 * Interfaz del servicio de pronóstico meteorológico. Incluye
 * métodos totalAmountOfRain and rainProbability que devuelven
 * la cantidad y probabilidad de lluvia pronosticada, respectivamente,
 * para unas coordenadas GPS y fecha determinadas.
 */
public interface IWeatherService {
    double totalAmountOfRain (GPSCoordinates coords, LocalDate date);
    double rainProbability (GPSCoordinates coords, LocalDate date);
}
