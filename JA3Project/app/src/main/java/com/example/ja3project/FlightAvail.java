package com.example.ja3project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightAvail extends AppCompatActivity {

    private static EditText numSeats;
    public static int numSeats2 = 1;
    private static RecyclerView availView;
    SharedPreferences sp;
    static Available availFlights;
    private static Context context;
    private static RecyclerView.LayoutManager lm;
    private static ArrayList<FlightModel> flightDetails = new ArrayList<>();
    private static ResultSet rs;
    private static int userID;

    public static int getNumSeats2() {
        return numSeats2;
    }

    public void goToBookedFlights(View view){
        Intent intent = new Intent(this, FlightBooked.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_avail);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        userID = sp.getInt("id", 0);
        context = this;
        availView = (RecyclerView) findViewById(R.id.availView);
        numSeats = findViewById(R.id.seatInput);
        AvailFlights af = new AvailFlights();
        numSeats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(numSeats.getText().toString().equals("")){

                }else {
                    numSeats2 = Integer.parseInt(numSeats.getText().toString());
                    availFlights.notifyDataSetChanged();
                }
            }
        });

        af.execute();


    }



    public void logout(View view){
        SharedPreferences sp =getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
                Intent intent = new Intent(context, main.class);
                startActivity(intent);
    }
    static class AvailFlights extends AsyncTask<Void, Void, Void> {
        int seats =1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            seats= numSeats2;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                rs = Database.availFlights();
                while (rs.next()) {
                    flightDetails.add(new FlightModel(
                            rs.getString("destination")
                            , rs.getString("departLoc")
                            , rs.getDate("departTime")
                            , rs.getTime("duration")
                            , rs.getInt("availSeats")
                            , rs.getInt("idFlight")
                            , rs.getDouble("price")
                    ));

                    availView.setHasFixedSize(true);
                    lm = new LinearLayoutManager(context);
                    availView.setLayoutManager(lm);

                    availFlights = new Available(flightDetails, context, userID);
                    availView.setAdapter(availFlights);


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