package org.iis24.services;

/**
 * Interfaz servicio de mapas. Incluye el método getCoordinates que
 * devuelve las coordenadas GPS de una localización descrita como
 * una cadena de texto.
 */
public interface IMapService {
    public GPSCoordinates getCoordinates(String locationDescription);
}

