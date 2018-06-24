package com.company.scribner.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){
            public void onTick(long millisecondsUntilDone){
                Log.i("seconds left", String.valueOf(millisecondsUntilDone/1000));
            }

            public void onFinish(){
                Log.i("seconds left","Finished");
            }
        }.start();


        /*********
         * BELOW WORKS MORE LIKE A STOP WATCH
         */
//        final Handler handler = new Handler();
//
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Time", "A second passed by");
//
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        handler.post(run);
    }
}
