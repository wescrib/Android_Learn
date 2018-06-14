package com.company.scribner.numberwang;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    public void insertNum(View v){

        EditText number = findViewById(R.id.number);
        int n = Integer.parseInt(number.getText().toString());


        Number input = new Number(n);
        if(input.isSquare() && input.isTriangular()){
            img.setImageResource(R.drawable.pusheencake);
        }
        else if(input.isSquare()){
            img.setImageResource(R.drawable.square);
        }
        else if(input.isTriangular()){
            img.setImageResource(R.drawable.triangle);
        }else{
            img.setImageResource(R.drawable.poop);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.imageResult);
    }

    public class Number {

        int n;

        public Number(int n){
            this.n = n;
        }

        public boolean isTriangular(){
            int x = 1;
            int triNum = 1;

            while(triNum < n){
                x++;
                triNum = triNum + x;
            }
            if(triNum == n){
                return true;
            }
            return false;
        }

        public boolean isSquare(){

            double sqNum = Math.sqrt(n);

            if(sqNum == Math.floor(sqNum)){
                return true;
            }
            return false;
        }
    }
}
