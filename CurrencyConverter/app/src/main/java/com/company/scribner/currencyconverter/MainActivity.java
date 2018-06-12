package com.company.scribner.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View v){

        EditText editD = findViewById(R.id.USD);

        String dollarVal = editD.getText().toString();
        TextView conRes = (TextView)findViewById(R.id.result);

        try{
            double dollar = Double.parseDouble(dollarVal);


            double yenVal = 110.35;
            double ruppeeVal = 67.48;
            double euroVal = 0.85;
            double poundVal = 0.75;
            double dirhamVal = 3.67;

            double result = 0.00;

            String c = "";


            switch(v.getId()){
                case R.id.yen:{
                    result = dollar*yenVal;
                    c = "¥";
                    break;
                }

                case R.id.ruppee:{
                    result = dollar*ruppeeVal;
                    c = "₹";
                    break;
                }

                case R.id.britishPound:{
                    result = dollar*poundVal;
                    c = "£";
                    break;
                }
                case R.id.euro:{
                    result = dollar*euroVal;
                    c = "€";
                    break;
                }

                case R.id.dirham:{
                    result = dollar*dirhamVal;
                    c = "د.إ";
                    break;
                }
            }

            Log.i("result", String.valueOf(result));
            conRes.setText(String.format(c + " %.2f",result));
        } catch(Exception e){
            conRes.setText("Why are there letters bro?");
        }
    }
}
