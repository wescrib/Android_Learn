 package com.company.scribner.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


 public class MainActivity extends AppCompatActivity {

    ListView listView;

     static ArrayList<String> places = new ArrayList<String>();
     static ArrayList<LatLng> locations = new ArrayList<LatLng>();
     static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.company.scribner.memorableplaces", Context.MODE_PRIVATE);

        ArrayList<String> lats = new ArrayList<>();
        ArrayList<String> longs = new ArrayList<>();

        places.clear();
        lats.clear();
        longs.clear();
        locations.clear();

        try{
            places = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>())));
            lats = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("latitudes", ObjectSerializer.serialize(new ArrayList<String>())));
            longs = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longitudes", ObjectSerializer.serialize(new ArrayList<String>())));
        }catch(Exception e) {

            e.printStackTrace();
        }
        if(places.size() > 0 && lats.size() > 0 && longs.size() > 0){
            if(places.size() == lats.size() && places.size() == longs.size()){
                for(int i=0; i< lats.size(); i++){
                    locations.add(new LatLng(Double.parseDouble(lats.get(i)), Double.parseDouble(longs.get(i))));
                }
            }
        }else{
            places.add("Add a new place!");
            locations.add(new LatLng(0,0));
        }

        listView = findViewById(R.id.listView);


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, places);

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
