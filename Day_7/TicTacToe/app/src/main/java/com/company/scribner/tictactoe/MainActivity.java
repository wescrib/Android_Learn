package com.company.scribner.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    //0 = yellow, 1 = red
    int activePlayer = 1;

    public void drop(View v){

        Log.i("Info", "hitting space");
        ImageView counter = (ImageView) v;

        counter.setTranslationY(-1500);

        switch(activePlayer){
            case 0:{
                counter.setImageResource(R.drawable.yellow);
                Log.i("Yellow", counter.getTag().toString());
                activePlayer = 1;
                break;
            }
            case 1:{
                counter.setImageResource(R.drawable.red);
                Log.i("Red", counter.getTag().toString());
                activePlayer = 0;
                break;
            }

        }

        counter.animate().translationYBy(1500).setDuration(400).rotation(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
