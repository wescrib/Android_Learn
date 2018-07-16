package com.company.scribner.sqldemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //create a table called user, its in regular SQL syntax
            SQLiteDatabase myDb = this.openOrCreateDatabase("Events", MODE_PRIVATE,null);
            myDb.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4))");
            myDb.execSQL("INSERT INTO events (name, year) VALUES('Millenium',2001)");
            myDb.execSQL("INSERT INTO events (name, year) VALUES('Apocalypse',2012)");

            //pointer
            Cursor c = myDb.rawQuery("SELECT * FROM events",null);

            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");


            //moves cursor to top row
            c.moveToFirst();


            String name;
            String year;


            while(c != null){

                name = c.getString(nameIndex);
                year = String.valueOf(c.getInt(yearIndex));
                //get data by column, in this case the name column
                Log.i("db", name + ":" + year);
                //moves cursor down row
                c.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
