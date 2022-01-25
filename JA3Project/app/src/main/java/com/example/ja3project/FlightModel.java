package com.example.ja3project;

import java.sql.Time;
import java.util.Date;

public class FlightModel {

    private String dest, departLoc;
    private Date departTime;
    private Time duration;
    private int seats, id;
    private double price;

    public FlightModel(String dest, String departLoc, Date departTime, Time duration, int seats, int id, double price) {
        this.dest = dest;
        this.departLoc = departLoc;
        this.departTime = departTime;
        this.duration = duration;
        this.seats = seats;
        this.id = id;
        this.price = price;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDepartLoc() {
        return departLoc;
    }

    public void setDepartLoc(String departLoc) {
        this.departLoc = departLoc;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
