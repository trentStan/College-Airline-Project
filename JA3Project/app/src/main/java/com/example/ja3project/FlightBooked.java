package com.example.ja3project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightBooked extends AppCompatActivity {

    private RecyclerView bookedView;
    private static SharedPreferences sp;
    Booked bookedFlights;
    private RecyclerView.LayoutManager lm;
    private static ArrayList<BookedFlightModel> flightDetails = new ArrayList<>();
    private static ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_avail);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        bookedView = (RecyclerView) findViewById(R.id.bookedView);
        bookedView.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        bookedView.setLayoutManager(lm);

        FlightAvail.AvailFlights af = new FlightAvail.AvailFlights();
        af.execute();

        bookedFlights = new Booked(flightDetails, this);
        bookedView.setAdapter(bookedFlights);
    }

    public void logout(View view){
        SharedPreferences sp =getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

    static class AvailFlights extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {



            try {
                rs = Database.bookedFlights(sp.getInt("user", 0));
                while (rs.next()) {
                    flightDetails.add(new BookedFlightModel(
                            rs.getDate("bookTime")
                            ,rs.getString("destination")
                            , rs.getString("departLoc")
                            , rs.getDate("departTime")
                            , rs.getTime("duration")
                            , rs.getInt("seats")
                            , rs.getInt("idFlight")
                            , rs.getDouble("price")
                            , rs.getInt("idPayment")
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

        }
    }
}