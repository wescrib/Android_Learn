package com.company.scribner.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
        if(toDoList.size()>1 && toDoList.get(0).equals("Note items go here")){
            toDoList.remove(0);
            arrayAdapter.notifyDataSetChanged();
            HashSet<String> set = new HashSet<>(MainActivity.toDoList);
            sp.edit().putStringSet("list_items",set).apply();
            testRemove(set);
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
                intent.putExtra("itemId", -1);
                startActivity(intent);
                return true;
            }
            default:
                return false;
        }
    }

    protected void testRemove(HashSet set){
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String s = iterator.next();
            if(s.equals("List Items will appear here")){
                iterator.remove();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = getApplication().getSharedPreferences("com.company.scribner.notes", Context.MODE_PRIVATE);
        HashSet < String > set = (HashSet < String > ) sp.getStringSet("list_items", null);

        init();
        if(set == null || set.size() == 0){
             toDoList.add("Note items go here");
        }else{
            testRemove(set);
            toDoList = new ArrayList(set);
        }


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toDoList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                Log.i("noteId", String.valueOf(position));
                intent.putExtra("itemId", position);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int itemToRemove = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Remove")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                toDoList.remove(itemToRemove);
                                arrayAdapter.notifyDataSetChanged();
                                HashSet<String> set = new HashSet<>(MainActivity.toDoList);
                                sp.edit().putStringSet("list_items",set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

    }
}
