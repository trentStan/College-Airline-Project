package com.example.ja3project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;


public class Available extends RecyclerView.Adapter<Available.ViewHolder>{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public Context context;
    static double total = 0;
    static int numseats3;
    public static int userID;
    FlightModel availFlightsDB;
    private static ArrayList<FlightModel> flightDetails = new ArrayList<>();



    public Available(ArrayList<FlightModel> dataset, Context context, int userID) {
        flightDetails = dataset;
        this.context = context;
        this.userID = userID;
        numseats3= FlightAvail.getNumSeats2();
    }



    @NonNull
    @Override
    public Available.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fragment_available, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Available.ViewHolder holder, int position) {
        FlightModel availFlights = flightDetails.get(position);
        availFlightsDB = availFlights;
        holder.ID.setText(String.valueOf(availFlights.getId()));
        holder.dest.setText(availFlights.getDest());
        holder.departLoc.setText(availFlights.getDepartLoc());
        holder.depTime.setText(availFlights.getDepartTime().toString());
        holder.duration.setText(availFlights.getDuration().toString());
        holder.price.setText(String.valueOf(availFlights.getPrice()));
        holder.seatsAvail.setText(String.valueOf(String.valueOf(availFlights.getSeats())));

        /*holder.numSeats.getText().toString()) > availFlights.getSeats() || */
        holder.bookBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = availFlights.getPrice() * FlightAvail.numSeats2;

                if( FlightAvail.numSeats2 <= availFlights.getSeats()  || FlightAvail.numSeats2 > 0 ){
                    System.out.println("Invoked");
                    bookToDB db = new bookToDB();
                    db.execute();

                }else{
                    holder.errMess.setVisibility(View.VISIBLE);
                }

                //context.startActivity(intent);
            }
        });

    }

    public double calcTotal(double price, double numSeats){
        return price* numSeats;
    };

    @Override
    public int getItemCount() {
        return flightDetails.size();
    }

    class bookToDB extends AsyncTask<Void,Void, Void>{




        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Database.bookFlight(availFlightsDB.getId(), userID, total, numseats3, availFlightsDB.getSeats() );

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ID , dest, departLoc, duration ,depTime ,price ,seatsAvail,  errMess;
        public EditText numSeats;
        public Button bookBut;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ID = itemView.findViewById(R.id.flightID);
            dest = (TextView) itemView.findViewById(R.id.dest);
            departLoc = (TextView) itemView.findViewById(R.id.departLoc);
            depTime = (TextView) itemView.findViewById(R.id.depTime);
            duration = (TextView) itemView.findViewById(R.id.duration);
            price = (TextView) itemView.findViewById(R.id.price);
            seatsAvail = (TextView) itemView.findViewById(R.id.seatAvail);

            bookBut = itemView.findViewById(R.id.button);

            errMess = itemView.findViewById(R.id.errMess3);


        }


    }


}