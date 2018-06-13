package com.company.scribner.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int n;

    public void guessBtn(View v){

        Random r = new Random();
        Log.i("random number", String.valueOf(n));
        EditText editTextGuess = findViewById(R.id.guess);

        String stringGuess = editTextGuess.getText().toString();

        int gNum = Integer.valueOf(stringGuess);

        TextView msgs = findViewById(R.id.messages);


        //insults
        String insult1 = "You shouldn't reproduce";
        String insult2 = "Were you the child that wasn't left behind?";
        String insult3 = "Wow, so you're actually dumb";
        String insult4 = "I hope you don't have a family to support";
        String insult5 = "Just turn me off";

        String[] insults = {insult1, insult2, insult3, insult4, insult5};

        //successes
        String success1 = "Congrats. Many apes can do what you just did";
        String success2 = "I'm sure your parent's are very proud of your single achievement";
        String success3 = "Thank god it's over. Do I sound like your significant other?";

        String[] successes = {success1, success2, success3};


        int randIndex = r.nextInt(insults.length);
        int randIndex2 = r.nextInt(successes.length);

        if(gNum > n){
            msgs.setText(insults[randIndex]);
            Toast.makeText(this,"Lower", Toast.LENGTH_SHORT).show();
        }

        if(gNum < n){
            msgs.setText(insults[randIndex]);
            Toast.makeText(this,"Higher", Toast.LENGTH_SHORT).show();
        }
        if(gNum > 50 || gNum < 0){
            msgs.setText("1 to 50, its literally the only rule");
            Toast.makeText(this,"Def not a valid input", Toast.LENGTH_LONG).show();
        }
        if(gNum == n){
            msgs.setText(successes[randIndex]);
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show();
            resetNumber();
        }

    }

    public void resetNumber(){
        Random r = new Random();
        n = r.nextInt(50)+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random r = new Random();

        n = r.nextInt(50)+1;
    }
}
