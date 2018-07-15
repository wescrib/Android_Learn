package com.company.scribner.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity {

    @BindView(R.id.editNoteView)
    EditText editNote;

    int itemId;

    SharedPreferences sp;

    Intent intent;

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.save:{
                String content = editNote.getText().toString();
                MainActivity.toDoList.add(content);
                try{
                    sp.edit().putString("list_items",ObjectSerializer.serialize(MainActivity.toDoList)).apply();
                    Toast.makeText(this, "Note Added!", Toast.LENGTH_SHORT).show();
                    backToMainActivity();

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, "There was an error saving", Toast.LENGTH_LONG).show();
                }
                return true;
            }
            default:
                return false;
        }
    }

    protected void backToMainActivity(){
        intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("com.company.scribner.notes", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        itemId = intent.getIntExtra("itemId", -1);
        if(itemId >= 0){
            editNote.setText(MainActivity.toDoList.get(itemId));
        }

        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.toDoList.set(itemId, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
//                Log.i("LIST ITEM",MainActivity.toDoList.get(itemId));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
