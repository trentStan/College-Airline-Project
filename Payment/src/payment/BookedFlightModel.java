package payment;

import java.sql.Time;
import java.util.Date;

public class BookedFlightModel {

    private Date bookingTime;
    private String dest, departLoc;
    private Date departTime;
    private Time duration;
    private int bookedSeats , flightId, paymentID;
    private double price, totalCost;
    private String isPaid;

    public BookedFlightModel(Date bookingTime, String dest, String departLoc, Date departTime, Time duration, int bookedSeats, int id, double price) {
        this.bookingTime = bookingTime;
        this.dest = dest;
        this.departLoc = departLoc;
        this.departTime = departTime;
        this.duration = duration;
        this.bookedSeats = bookedSeats;
        
        this.price = price;
    }

    public BookedFlightModel(Date bookingTime, String dest, String departLoc, Date departTime, int bookedSeats, int flightId, int paymentID, double totalCost) {
        this.bookingTime = bookingTime;
        this.dest = dest;
        this.departLoc = departLoc;
        this.departTime = departTime;
        this.bookedSeats = bookedSeats;
        this.flightId = flightId;
        this.paymentID = paymentID;
        this.totalCost = totalCost;
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

    public int getFlightId() {
        return flightId;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    

    public double getPrice() {
        return price;
    }
}
