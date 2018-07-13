package com.company.scribner.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.company.scribner.sharedpreferences", Context.MODE_PRIVATE);

        //save info into shared preferences
        sharedPreferences.edit().putString("username","nick").apply();

        //get info out of shared preferences
        String username = sharedPreferences.getString("username","N/A");

        Toast.makeText(this, username,Toast.LENGTH_LONG).show();
    }
}
