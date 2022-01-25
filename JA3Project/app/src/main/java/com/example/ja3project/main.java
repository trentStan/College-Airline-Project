package com.example.ja3project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class main extends AppCompatActivity {

    private static SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        Database createDB = new Database();
        createDB.execute();
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sp.getInt("id", 0) == 0){
            intent = new Intent(this, login.class);
        }else{
            intent = new Intent(this, FlightAvail.class);
        }

        
        startActivity(intent);

    }



}

