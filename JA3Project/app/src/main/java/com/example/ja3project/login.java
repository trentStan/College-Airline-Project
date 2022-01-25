package com.example.ja3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class login extends AppCompatActivity {
    private static SharedPreferences sp;
    private static EditText username;
    private static EditText psw;
    static TextView errMessage;
    private static boolean checker = false;
    private static int idChecker = 0;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
         username = findViewById(R.id.inputUser);
         psw = findViewById(R.id.inputPass);
         errMessage = findViewById(R.id.errMess);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

    }

    public void checkCredentials(View view) throws SQLException, JSONException, IOException, ClassNotFoundException, InterruptedException {

        CheckCred db = new CheckCred();
        db.execute();
      /*
        if(!checker){
        System.out.println("incorrect credentials");
        errMessage.setVisibility(View.VISIBLE);
        } else if(checker){
            Intent intent = new Intent(this, FlightAvail.class);
            startActivity(intent);
        } */
    }
    public void register(View view){
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);
    }
    static class CheckCred extends AsyncTask<Void,Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                idChecker = Database.getID(username.getText().toString(), psw.getText().toString());
                if(idChecker > 0 ){
                    System.out.println("pass");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("id", idChecker);
                    editor.commit();
                    checker = true;
                    if(!checker){
                        System.out.println("incorrect credentials");
                        errMessage.setVisibility(View.VISIBLE);
                    } else if(checker){
                        Intent intent = new Intent(context, FlightAvail.class);

                       context.startActivity(intent);
                    }
                }else{

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }
    }
}