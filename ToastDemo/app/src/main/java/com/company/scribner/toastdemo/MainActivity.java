package com.company.scribner.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.pusheen);
    }

    public void btnPressed(View v){
        switch(v.getId()) {
            case R.id.button: {
                //if flag = true and the button is pressed
                if (flag) {
                    //switch image to regular pusheen
                    //this line below is literally going into the drawable folder inside your res (Resources) and finding the image names in there.
                    image.setImageResource(R.drawable.pusheen);
                    flag = false;
                    Toast.makeText(this,"FLUFFY!", Toast.LENGTH_SHORT).show();
                } else {
                    image.setImageResource(R.drawable.muscle_pusheen);
                    flag = true;
                    Toast.makeText(this,"MUSCLES!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
