package com.company.scribner.multiplicationtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {


    ListView numListView;

    public void generateMultiTable(int tableNum){
        ArrayList<Integer> numbersArrayList = new ArrayList<Integer>();
        for(int i = 1; i<=100; i++){
            numbersArrayList.add(i*tableNum);
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_selectable_list_item, numbersArrayList);

        numListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /***********************
         * SeekBar
         ***********************/
        final SeekBar multiNumBar = findViewById(R.id.seekBar);
        int max = 20;
        int start = 1;

        numListView = findViewById(R.id.listView);

        multiNumBar.setMax(max);
        multiNumBar.setProgress(start);

        generateMultiTable(start);



        multiNumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int tableNum;

                if(progress < min) {
                    tableNum = min;
                    multiNumBar.setProgress(min);
                }else{
                    tableNum = progress;
                }

                Log.i("Value",Integer.toString(tableNum));

                generateMultiTable(tableNum);




                numListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
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
