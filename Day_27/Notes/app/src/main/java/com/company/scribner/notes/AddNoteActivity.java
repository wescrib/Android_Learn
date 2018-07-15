package com.company.scribner.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

public class AddNoteActivity extends AppCompatActivity {

    @BindView(R.id.editNoteView)
    EditText editNote;

    int noteId;

    SharedPreferences sp;

    Intent intent;

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
        sp = this.getSharedPreferences("com.company.scribner.notes", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("itemId", -1);
        if(noteId > 0){
            editNote.setText(MainActivity.toDoList.get(noteId));
        }
    }
}
