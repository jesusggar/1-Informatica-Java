package org.iis24.services;

import java.time.LocalDate;
import java.util.LinkedList;

public class RobustShouldIGoToService extends ShouldIGoToService{

    LinkedList<IMapService> mapServices = new LinkedList<IMapService>();
    LinkedList<IWeatherService> weatherServices = new LinkedList<IWeatherService>();

    public RobustShouldIGoToService(LinkedList<IMapService> ms, LinkedList<IWeatherService> ws){
        super();
        for (IMapService mapService: ms) {
            if (mapService==null) {
                throw (new InvalidServiceInstanceException("Invalid map service instance found in list"));
            }
        }

        for (IWeatherService weatherService: ws) {
            if (weatherService==null){
                throw (new InvalidServiceInstanceException("Invalid weather service instance found in list"));
            }
        }

        this.mapServices = ms;
        this.weatherServices = ws;

    }

    @Override
    public boolean shouldIGoTo(String where, LocalDate when){
        GPSCoordinates myCoords=null;
        double avgRainProb=0.0;
        double avgRainAmount=0.0;

        for (IMapService mapService: this.mapServices){
            try {
                myCoords = mapService.getCoordinates(where);
            } catch (Exception e) {
                throw new InvalidMapServiceExecutionException("Map service received invalid input.");
            }
            if (myCoords!=null){
                break;
            }
        }

        // Comprobamos que la fecha sobre la que realizamos la consulta no haya pasado
        if (when.isBefore(LocalDate.now())) {
            throw new RuntimeException("Date provided is in the past.");
        } else {
            for (IWeatherService weatherService: this.weatherServices) {
                avgRainProb += weatherService.rainProbability(myCoords, when);
                avgRainAmount += weatherService.totalAmountOfRain(myCoords, when);
            }
        }

        avgRainProb /= this.weatherServices.size();
        avgRainAmount /= this.weatherServices.size();
        System.out.println(avgRainProb);
        // Decisión sobre si se debería visitar el lugar
        if (avgRainProb!=0 && avgRainProb >= this.getRainProbabilityThreshold()
                && avgRainAmount >= this.getRainAmountThreshold())
            return false;
        else return true;
    }
}
