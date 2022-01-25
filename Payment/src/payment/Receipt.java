/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author user-pc
 */
public class Receipt {
    String path;
    FileWriter myWriter;
    BookedFlightModel flightDetails;
    public Receipt(String username, BookedFlightModel flightDetails ,String path, String name) throws IOException {
        this.flightDetails = flightDetails;
        File receipt = new File(path +File.separator +"Receipt" +  name + ".txt" );
        
        System.out.println("Receipt");
        if (receipt.createNewFile()) {
            myWriter = new FileWriter(receipt.getAbsoluteFile());
           
            myWriter.write("Username: \t" + username 
                    + "\ndepart location: " + flightDetails.getDepartLoc()
                    + "\ndestination: \t" + flightDetails.getDest()
                    + "\ndepartTime: \t" + flightDetails.getDepartTime().toString()
                    + "\nDuration: \t" + flightDetails.getDuration()
                    + "\ntotal cost: \t" + flightDetails.getTotalCost()
            + "\nnumber of seats: " + flightDetails.getBookedSeats());
            myWriter.close();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().edit(receipt);
            } 
        } else {
            myWriter = new FileWriter(receipt.getName());
            myWriter.write("Username: \t" + username 
                    + "\ndepart location: \t" + flightDetails.getDepartLoc()
                    + "\ndestination: \t" + flightDetails.getDest()
                    + "\ndepartTime: \t" + flightDetails.getDepartTime().toString()
                    + "\nDuration: \t" + flightDetails.getDuration()
                    + "\ntotal cost: \t" + flightDetails.getTotalCost()
            + "\nnumber of seats: \t" + flightDetails.getBookedSeats());
            myWriter.close();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().edit(receipt);
            } 
        }
    }
    
    
}
