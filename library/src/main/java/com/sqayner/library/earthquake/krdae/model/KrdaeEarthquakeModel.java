package com.sqayner.library.earthquake.krdae.model;

import com.sqayner.library.earthquake.krdae.util.MD5;

import java.io.Serializable;
import java.util.Date;

public class KrdaeEarthquakeModel implements Serializable {

    private String location;
    private LatLongModel coordinates;
    private Double magnitude;
    private Double depth;
    private Date datetime;
    private RevisedModel revised;

    public KrdaeEarthquakeModel(String location, LatLongModel coordinates, Double magnitude, Double depth, Date datetime, RevisedModel revised) {
        this.location = location;
        this.coordinates = coordinates;
        this.magnitude = magnitude;
        this.depth = depth;
        this.datetime = datetime;
        this.revised = revised;
    }

    public KrdaeEarthquakeModel() {
    }

    @Override
    public String toString() {
        return "KrdaeEarthquakeModel{" +
                "location='" + location + '\'' +
                ", coordinates=" + coordinates +
                ", magnitude=" + magnitude +
                ", depth=" + depth +
                ", datetime=" + datetime +
                ", revised=" + revised +
                ", idHash=" + getIdHash() +
                ", hash=" + getHash() +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLongModel getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLongModel coordinates) {
        this.coordinates = coordinates;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public RevisedModel getRevised() {
        return revised;
    }

    public void setRevised(RevisedModel revised) {
        this.revised = revised;
    }

    public String getIdHash() {
        return MD5.encode((coordinates == null ? "" : +coordinates.getLatitude()) + "," + (coordinates == null ? "" : +coordinates.getLongitude()));
    }

    public String getHash() {
        return MD5.encode((magnitude == null ? "" : magnitude) + "," + (coordinates == null ? "" : +coordinates.getLatitude()) + "," + (coordinates == null ? "" : +coordinates.getLongitude()) + "," + (depth == null ? "" : +depth) + "," + (datetime == null ? "" : +datetime.getTime()) + "," + (location == null ? "" : location));
    }
}
