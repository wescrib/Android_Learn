 package com.company.scribner.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

 public class MainActivity extends AppCompatActivity {

    ListView listView;

    static ArrayList<String> locs = new ArrayList<String>();

    static ArrayList<LatLng> coords = new ArrayList<LatLng>();

     static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        locs.add("Add a new place!");
        coords.add(new LatLng(0,0));

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locs);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("placeNum", position);

                startActivity(intent);
            }
        });

    }
}
