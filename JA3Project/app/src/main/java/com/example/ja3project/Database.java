package com.example.ja3project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Database extends AsyncTask<Void, Void, Void> {
    private static SharedPreferences sp;
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;

    public static ResultSet bookedFlights(int userId) throws SQLException {
        st= conn.createStatement();
        return st.executeQuery("SELECT * FROM `payment`, `booking` , `Flight` WHERE `Flight_idFlight` = `idFlight` AND `User_idUser` = " + userId);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            DBConnection();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mysql://192.168.0.176:3306/airline", "airlineDB", "airlineDB");
        //Log.wtf("", ("Connection: " + conn)); // 192.168.0.176:3306
        System.out.println("Connection: " + conn);
    }


    public static int getID (String username, String psw) throws SQLException {
        System.out.println("Connection: " + conn);


        pst = conn.prepareStatement("SELECT `idUser` , `username`, `password` FROM `user` WHERE `username` = ? ");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            if (psw.equals(rs.getString("password"))) {
                return rs.getInt("idUser");
            }
        }
        return rs.getInt("idUser");
    }

    public static ResultSet availFlights() throws SQLException {
        st = conn.createStatement();
        return st.executeQuery("SELECT * FROM `flight` WHERE `availSeats` > 0");
    }

    public static void bookFlight(int flightID, int userID, double amt, int seats, int availSeats) throws SQLException {
        st = conn.createStatement();

        st.execute("INSERT INTO `payment`( `amt`) VALUES ("+amt+")");
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS `payid`");
        int payID = 0;

        while(rs.next()){
            payID = rs.getInt("payid");
        }
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        pst = conn.prepareStatement("INSERT INTO `booking`(`User_idUser`, `Flight_idFlight`, `Payment_idPayment`, `bookTime`, `seats`) VALUES (?,?,?,?,?)");
        pst.setInt(1, userID);
        pst.setInt(2, flightID);
        pst.setInt(3, payID);
        pst.setTimestamp(4, dateTime);
        pst.setInt(5, seats);
        pst.execute();

        pst = conn.prepareStatement("UPDATE `flight` SET `availSeats`= ? WHERE `idFlight` = ?" );
        pst.setInt(1, (availSeats - seats));
        pst.setInt(2, flightID);
        pst.execute();
    }

    public static int createUser(String name,String surname,String username,String email,double balance,String psw) throws SQLException {


        try{
            pst = conn.prepareStatement("INSERT INTO `user`(`username`, `password`, `Name`, `Surname`, `email`,  `Balance`) VALUES (?,?,?,?,?,?)");

            pst.setString(1, username);
            pst.setString(2, psw);
            pst.setString(3, name);
            pst.setString(4, surname);
            pst.setString(5, email);
            pst.setDouble(6, balance);

            pst.execute();

        } catch (Exception e){
            return 0;
        }
        return getID(username, psw);
    }
}
