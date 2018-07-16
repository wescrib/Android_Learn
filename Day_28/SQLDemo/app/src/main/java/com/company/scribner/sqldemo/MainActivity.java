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

        SQLiteDatabase myDb = this.openOrCreateDatabase("Users", MODE_PRIVATE,null);

        //create a table called user, its in regular SQL syntax
        myDb.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

         myDb.execSQL("INSERT INTO users (name, age) VALUES('William',26)");
        myDb.execSQL("INSERT INTO users (name, age) VALUES('Elaine',7)");

        Cursor c = myDb.rawQuery("SELECT * FROM users",null);

        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");


        //moves cursor to top row
        c.moveToFirst();

        String name;
        String age;

        while(c != null){

            name = c.getString(nameIndex);
            age = String.valueOf(c.getInt(ageIndex));
            //get data by column, in this case the name column
            Log.i("db", name + "," + age);
            //moves cursor down row
            c.moveToNext();
        }
    }
}
