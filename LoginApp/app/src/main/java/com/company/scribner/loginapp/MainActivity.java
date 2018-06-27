package com.company.scribner.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        EditText usernameEditText = findViewById(R.id.usernameField);

        EditText passwordField = findViewById(R.id.passwordField);

        Log.i("username",usernameEditText.getText().toString());
        Log.i("password", passwordField.getText().toString());

        String user = usernameEditText.getText().toString();
        String pw = passwordField.getText().toString();

        Toast.makeText(this,user + " " + pw, Toast.LENGTH_SHORT).show();
    }
}
