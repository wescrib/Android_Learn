package com.company.scribner.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {





    public void prevPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);

//        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        TextView textView = findViewById(R.id.textView2);

        Intent intent = getIntent();
        int age = intent.getIntExtra("age",0);
        textView.setText(String.valueOf(age));
    }
}
