package com.company.scribner.interactivedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pressBtn(View v){

        EditText nameEditText = findViewById(R.id.nameEditTex);


        Log.i("Values",nameEditText.getText().toString());
    }
}
