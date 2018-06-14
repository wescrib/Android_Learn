package com.company.scribner.numberwang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void insertNum(View v){

        EditText number = findViewById(R.id.number);
        int n = Integer.parseInt(number.getText().toString());


        Number input = new Number(n);

        if(input.isSquare()){

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
