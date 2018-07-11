package com.company.scribner.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        TextView textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String person = intent.getStringExtra("name");
        textView.setText(person);
    }
}
