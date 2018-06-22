package com.company.scribner.basicphrases;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String audioFile="R.raw.hello";
    Uri uri = Uri.parse(audioFile);

    public void play(View view){
        Button btn = (Button) view;


        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(btn.getTag().toString(), "raw", getPackageName()));

        mediaPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
