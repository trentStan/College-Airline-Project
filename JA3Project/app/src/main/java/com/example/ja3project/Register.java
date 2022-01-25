package com.example.ja3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.sql.SQLException;

public class Register extends AppCompatActivity {

    private static SharedPreferences sp;
    private static int id;
    static TextView errMessage;
    Button register;
    static EditText name, surname, username,  email, balance, psw, conPsw;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        name = findViewById( R.id.inputName);
        surname = findViewById(R.id.inputSur);
        username= findViewById(R.id.inputUser);
        errMessage = findViewById(R.id.pswMatch);
        email= findViewById(R.id.inputEmail);
        balance= findViewById(R.id.balance);
        psw= findViewById(R.id.inputPass);
        conPsw= findViewById(R.id.conPsw);
        register= findViewById(R.id.Register);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    public void backButton(View view) {
        super.onBackPressed();
    }

    public void register(View view) {
        if(!psw.getText().toString().equals(conPsw.getText().toString())){
            System.out.println("incorrect credentials");
            errMessage.setVisibility(View.VISIBLE);
        }else{
            CreateUser cr = new CreateUser();
            cr.execute();
            if(id != 0){

            }else{
                System.out.println("User Exists");
                errMessage.setVisibility(View.VISIBLE);
                errMessage.setText("User Exists");
            }
        }
    }

    static class CreateUser extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... voids) {

            try {
                id = Database.createUser(name.getText().toString(),surname.getText().toString(),username.getText().toString(),email.getText().toString() ,Integer.parseInt(balance.getText().toString())  ,psw.getText().toString());
                if (id != 0) {
                    System.out.println("Registered");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("id", id);
                    editor.commit();
                    Intent intent = new Intent(context, FlightAvail.class);
                    context.startActivity(intent);
                }else{

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }
    }
}