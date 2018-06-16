package com.company.scribner.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red, 2 - empty
    int activePlayer = 1;
    int[][] winningPostures = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gameActive = true;

    public void drop(View v) {

        Log.i("Info", "hitting space");
        ImageView counter = (ImageView) v;

        //basically filling the array with the active player int, so the game knows when someone has won
        //tappedCounter = where the player touches the grid
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        String w = "";


        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            switch (activePlayer) {
                case 0: {
                    counter.setImageResource(R.drawable.yellow);
                    Log.i("Yellow", counter.getTag().toString());
                    activePlayer = 1;
                    break;
                }
                case 1: {
                    counter.setImageResource(R.drawable.red);
                    Log.i("Red", counter.getTag().toString());
                    activePlayer = 0;
                    break;
                }

            }

            counter.animate().translationYBy(1500).setDuration(400).rotation(1000);


            for (int[] wp : winningPostures) {
                if (gameState[wp[0]] != 2 && gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]]) {

                    switch (activePlayer) {
                        case 0: {
                            w = "Red";
                            break;
                        }
                        case 1: {
                            w = "Yellow";
                            break;
                        }

                    }
                    gameActive = false;

                    Button playAgainButton = findViewById(R.id.playAgainBtn);

                    TextView winnerText = findViewById(R.id.winnterText);

                    winnerText.setText(w + " won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View v){
        Button playAgainButton = findViewById(R.id.playAgainBtn);

        TextView winnerText = findViewById(R.id.winnterText);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        drop(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
