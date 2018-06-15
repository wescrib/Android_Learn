package com.company.scribner.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean check = false;

    public void transform(View v){

        ImageView img;
        ImageView img2;
        img = findViewById(R.id.pikachu);
        img2 = findViewById(R.id.raichu);
        switch(v.getId()){
            case R.id.raichu:{
                if(check){
                    img2.animate().alpha(1).setDuration(2000);
                    img.animate().alpha(0).setDuration(2000);
                    check = false;
                }else{
                    img2.animate().alpha(0).setDuration(2000);
                    img.animate().alpha(1).setDuration(2000);
                    check = true;
                }
                break;
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
