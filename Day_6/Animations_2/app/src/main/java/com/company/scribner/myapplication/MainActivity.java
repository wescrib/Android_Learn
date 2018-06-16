package com.company.scribner.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int step = 0;

    public void transform(View v)
    {
        ImageView img = findViewById(R.id.gypsiedanger);
        if(step == 0){


            img.animate().rotation(1800).setDuration(1000);

            img.animate().translationYBy(1500).setDuration(2000);
            step++;
        }
        else if(step ==1){
            img.animate().translationXBy(-1000).setDuration(1000);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
