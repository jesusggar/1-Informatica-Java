package org.iis24.services;

public class GPSCoordinates {
    private double longitude, latitude;

    GPSCoordinates (double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }
}
