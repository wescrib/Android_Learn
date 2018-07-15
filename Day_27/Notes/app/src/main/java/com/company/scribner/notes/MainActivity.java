package com.company.scribner.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.listView)
    ListView listView;

    static ArrayList<String> toDoList = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;

    SharedPreferences sp;
    Intent intent;


    protected void init(){
        title.setRotation(-10);
        toDoList.clear();
    }

    protected void checkListSize(){
        if(toDoList.isEmpty()) {
            toDoList.add("List Items will appear here");

        }else{
            if(toDoList.get(0).equals("List Items will appear here")){
                toDoList.remove(0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.addNote:{
                intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("com.company.scribner.notes", Context.MODE_PRIVATE);
        init();

        try{
            toDoList = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("list_items", ObjectSerializer.serialize(new ArrayList<String>())));
        }catch(Exception e){
            e.printStackTrace();
        }

        checkListSize();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toDoList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                intent.putExtra("itemId", position);
                startActivity(intent);
            }
        });

    }
}
