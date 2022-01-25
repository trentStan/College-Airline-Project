package payment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;

    public static ResultSet bookedFlights(int userId) throws SQLException {
        st = conn.createStatement();
        return st.executeQuery("SELECT * FROM `payment`, `booking` , `Flight` WHERE `Flight_idFlight` = `idFlight` AND `User_idUser` = " + userId);
    }

    public static void updatePayment(int payID) throws SQLException{
        pst = conn.prepareStatement("UPDATE `payment` SET `paid`=? WHERE `idPayment` = ?");
        pst.setString(1, "y");
        pst.setInt(2, payID);
        pst.execute();
    }
    
    public static void reduceBalance(int userID, double amt) throws SQLException {
        pst = conn.prepareStatement("UPDATE `user` SET `Balance`=? WHERE `idUser` = ? ");
        double newAmt = getUserBal(userID) - amt;
        pst.setDouble(1, newAmt);
        pst.setInt(2, userID);
        pst.execute();
    }

    public static ArrayList<BookedFlightModel> unpaidFlights(int userId) throws SQLException {
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `payment`, `booking` , `Flight` WHERE `Flight_idFlight` = `idFlight` AND `paid` = 'n' AND +`Payment_idPayment` = `idPayment` AND`User_idUser` = " + userId);
        ArrayList<BookedFlightModel> unpaidList = new ArrayList<>();
        while (rs.next()) {
            unpaidList.add(new BookedFlightModel(
                    rs.getDate("bookTime"),
                    rs.getString("destination"),
                    rs.getString("departLoc"),
                    rs.getDate("departTime"),
                    rs.getInt("availSeats"),
                    rs.getInt("idFlight"),
                    rs.getInt("idPayment"),
                    rs.getDouble("amt")
            ));
        }

        return unpaidList;
    }

    public static void DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mysql://192.168.0.176:3306/airline", "airlineDB", "airlineDB");
        //Log.wtf("", ("Connection: " + conn)); // 192.168.0.176:3306
        System.out.println("Connection: " + conn);
    }

    public static double getUserBal(int userID) throws SQLException {
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT `Balance` FROM `user` WHERE `idUser` = " + userID);
        double balance = 0;
        while (rs.next()) {
            balance = rs.getDouble("Balance");
        }

        return balance;
    }

    public static int getID(String username, String psw) throws SQLException {

        st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT `idUser` , `username`, `password` FROM `user` WHERE `username` = '" + username + "' ");

        while (rs.next()) {

            if (psw.equals(rs.getString("password"))) {
                return rs.getInt("idUser");
            }
        }
        return 0;
    }

    public static ResultSet availFlights() throws SQLException {
        st = conn.createStatement();
        return st.executeQuery("SELECT * FROM `flight` WHERE `availSeats` > 0");
    }

}
