package com.example.ja3project;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Booked extends RecyclerView.Adapter<Booked.ViewHolder> {

    public Context context;

    private static ArrayList<BookedFlightModel> flightDetails;

    public Booked(ArrayList<BookedFlightModel> dataset, Context context) {
        flightDetails = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public Booked.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Booked.ViewHolder holder, int position) {
        BookedFlightModel availFlights = flightDetails.get(position);
        holder.ID.setText(String.valueOf(availFlights.getId()));
        holder.dest.setText(availFlights.getDest());
        holder.departLoc.setText(availFlights.getDepartLoc());
        holder.depTime.setText(availFlights.getDepartTime().toString());
        holder.duration.setText(availFlights.getDuration().toString());
        holder.costTotal.setText(String.valueOf(availFlights.getPrice()));
        holder.seatsBook.setText(String.valueOf(availFlights.getBookedSeats()));

        holder.canBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }

    @Override
    public int getItemCount() {
        return flightDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView isPaid;
        public TextView ID, dest, departLoc, duration, depTime, costTotal, seatsBook;
        public Button canBut;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ID = itemView.findViewById(R.id.flightID);
            dest = (TextView) itemView.findViewById(R.id.dest2);
            departLoc = (TextView) itemView.findViewById(R.id.departLoc2);
            depTime = (TextView) itemView.findViewById(R.id.bookDepTime);
            duration = (TextView) itemView.findViewById(R.id.bookDur);
            costTotal = (TextView) itemView.findViewById(R.id.CostTotal);
            seatsBook = (TextView) itemView.findViewById(R.id.seatBook);
            isPaid = (TextView) itemView.findViewById(R.id.isPaid);
            canBut = itemView.findViewById(R.id.cancelBook);

        }

    }
}
