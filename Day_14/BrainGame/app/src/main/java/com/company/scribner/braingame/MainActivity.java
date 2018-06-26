package com.company.scribner.braingame;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumTextView;
    TextView statusMsg;
    TextView scoreView;
    TextView timeLeftview;

    Button enterBtn;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;

    CountDownTimer countDownTimer;

    ConstraintLayout gameLayoutView;

    int score = 0;
    int questionNum = 0;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int correctAnswerLocation;

    public void playAgain(View view){
        score = 0;
        questionNum = 0;
        scoreView.setText(Integer.toString(score)+"/" + Integer.toString(questionNum));
        startCountdown();
        newProblem();
    }

    public void startCountdown(){
        timeLeftview.setText("30");
        countDownTimer = new CountDownTimer(3100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftview.setText(String.valueOf(millisUntilFinished/1000));
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                playAgainBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                statusMsg.setText("Finished!");
                playAgainBtn.setVisibility(View.VISIBLE);
                btn0.setEnabled(false);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        };

        countDownTimer.start();
    }

    public void start(View view){
        enterBtn.setVisibility(View.INVISIBLE);
        gameLayoutView.setVisibility(View.VISIBLE);
        newProblem();
        startCountdown();
    }

    public void newProblem(){
        Random r = new Random();
        correctAnswerLocation = r.nextInt(4);

        int x = r.nextInt(21);
        int y = r.nextInt(21);

        sumTextView = findViewById(R.id.problem);

        sumTextView.setText(Integer.toString(x) + " + " + Integer.toString(y));
        answers.clear();
        for(int i=0; i<4; i++){
            if(i == correctAnswerLocation){
                answers.add(x+y);
            }else{
                int incorrectAnswer = r.nextInt(41);
                //if the randomly generated incorrect answer is actually the right answer, then itll generate a new incorrect answer
                while(incorrectAnswer == x+y){
                    incorrectAnswer = r.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        populateAnswers(answers);
    }

    public void chooseAnswer(View view){

        String selected = view.getTag().toString();
        String correctAnswer = Integer.toString(correctAnswerLocation);



        if(correctAnswer.equals(selected)){
            score++;

            statusMsg.setText("Correct!");
            statusMsg.setVisibility(View.VISIBLE);
        }else{
            statusMsg.setText("Incorrect!");
            statusMsg.setVisibility(View.VISIBLE);
        }
        questionNum++;
        scoreView.setText(Integer.toString(score)+"/" + Integer.toString(questionNum));
        newProblem();
    }

    private void populateAnswers(ArrayList arrayList){

        btn0.setText(Integer.toString((int)answers.get(0)));
        btn1.setText(Integer.toString((int)answers.get(1)));
        btn2.setText(Integer.toString((int)answers.get(2)));
        btn3.setText(Integer.toString((int)answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMsg = findViewById(R.id.status);
        scoreView = findViewById(R.id.score);
        timeLeftview = findViewById(R.id.timeLeft);

        /*************
         * BUTTONS
         *************/

        enterBtn = findViewById(R.id.enterBtn);
        btn0 = findViewById(R.id.option0);
        btn1 = findViewById(R.id.option1);
        btn2 = findViewById(R.id.option2);
        btn3 = findViewById(R.id.option3);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        gameLayoutView = findViewById(R.id.gameLayout);

        gameLayoutView.setVisibility(View.INVISIBLE);
        enterBtn.setVisibility(View.VISIBLE);



    }
}
