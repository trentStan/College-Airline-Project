/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.sql.SQLException;

/**
 *
 * @author user-pc
 */
public class Payment {

    BookedFlightModel unpaidBook;
    int userID;

    public Payment(BookedFlightModel unpaidBook, int userID) throws SQLException {
        this.unpaidBook = unpaidBook;
        this.userID = userID;
        
        Database.updatePayment(this.unpaidBook.getPaymentID());
        Database.reduceBalance(userID, this.unpaidBook.getTotalCost());
    }
    
    
    
    
    
}
