package com.company.scribner.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView displayTimeLeft;

    SeekBar timeBar;

    Button startBtn;

    boolean isStarted = false;

    CountDownTimer countDownTimer;


    public void startCountdown(View view) {


        startBtn = findViewById(R.id.startButton);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.meow);
        if (!isStarted) {
            countDownTimer = new CountDownTimer(timeBar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long msUntilDone) {
                    updateCountdown((int)msUntilDone/1000);
                }

                public void onFinish() {
                    mediaPlayer.start();
                    resetCountdown();

                }
            };


            startBtn.setText("Stop");
            isStarted = true;
            timeBar.setEnabled(false);
            countDownTimer.start();
        } else if (isStarted) {
            resetCountdown();
        }

    }

    public void updateCountdown(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }

        displayTimeLeft.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    public void resetCountdown(){
        startBtn.setText("Start");
        timeBar.setProgress(30);
        displayTimeLeft.setText("0:30");
        isStarted = false;
        timeBar.setEnabled(true);
        countDownTimer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTimeLeft = findViewById(R.id.timeLeft);


        /**********************
         * SeekBar
         **********************/

        timeBar = findViewById(R.id.seekBar);
        final int min = 1;
        int max = 600;
        int defaultTime = 30;
        int timeLength;

        timeBar.setMax(max);
        timeBar.setProgress(defaultTime);

        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateCountdown(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
