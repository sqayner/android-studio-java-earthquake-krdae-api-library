package com.sqayner.library.earthquake.krdae.model;

import java.io.Serializable;

public class LatLongModel implements Serializable {

    private Double latitude;
    private Double longitude;

    public LatLongModel() {
    }

    public LatLongModel(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatLongModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
