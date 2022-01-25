package com.example.ja3project;

import java.sql.Time;
import java.util.Date;

public class BookedFlightModel {

    private Date bookingTime;
    private String dest, departLoc;
    private Date departTime;
    private Time duration;
    private int bookedSeats , id, payID;
    private double price;
    private String isPaid;

    public BookedFlightModel(Date bookingTime, String dest, String departLoc, Date departTime, Time duration, int bookedSeats, int id, double price, int payID) {
        this.bookingTime = bookingTime;
        this.dest = dest;
        this.departLoc = departLoc;
        this.departTime = departTime;
        this.duration = duration;
        this.bookedSeats = bookedSeats;
        this.id = id;
        this.price = price;
        this.payID = payID;
    }

    public int getPayID() {
        return payID;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public String getDest() {
        return dest;
    }

    public String getDepartLoc() {
        return departLoc;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public Time getDuration() {
        return duration;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
