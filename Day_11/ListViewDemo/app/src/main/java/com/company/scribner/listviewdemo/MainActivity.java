package com.company.scribner.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> travelDestinations = new ArrayList<String>(asList(
                "Dominican Republic",
                "Chile",
                "Morocco",
                "Algeria",
                "Czech Republic",
                "South Africa",
                "Lebanon",
                "Colombia",
                "Bolivia",
                "Kenya",
                "Spain",
                "Portugal",
                "Panama",
                "Japan",
                "China",
                "Canada",
                "France",
                "Tajikistan",
                "Italy",
                "The Moon"

        ));
        ListView myList = findViewById(R.id.listView);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, travelDestinations);

        myList.setAdapter(arrayAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"I want to go to " + travelDestinations.get(position), LENGTH_SHORT).show();
            }
        });



    }
}
