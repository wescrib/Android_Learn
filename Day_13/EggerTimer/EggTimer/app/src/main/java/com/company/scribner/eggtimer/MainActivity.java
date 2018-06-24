package com.company.scribner.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView displayTimeLeft;

    Button startBtn;

    boolean isStarted = false;

    public void startCountdown(View view){

        displayTimeLeft = findViewById(R.id.timeLeft);
        startBtn = findViewById(R.id.startButton);

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long msUntilDone) {
                displayTimeLeft.setText(String.valueOf(msUntilDone / 1000));
            }

            public void onFinish() {

            }
        };

        if(!isStarted) {
            startBtn.setText("Stop");
            countDownTimer.start();

            isStarted = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
