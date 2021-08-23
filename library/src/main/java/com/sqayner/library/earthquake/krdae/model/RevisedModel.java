package com.sqayner.library.earthquake.krdae.model;

import java.io.Serializable;
import java.util.Date;

public class RevisedModel implements Serializable {

    private int number;
    private Date datetime;

    public RevisedModel() {
    }

    public RevisedModel(int number, Date datetime) {
        this.number = number;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "RevisedModel{" +
                "number=" + number +
                ", datetime=" + datetime +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
