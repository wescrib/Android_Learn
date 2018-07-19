package com.company.scribner.newsfeed;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    ListView listView;

    SQLiteDatabase articleDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleDb = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articleDb.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, artcleId INTEGER, title VARCHAR, content VARCHAR)");

        DownloadTask task = new DownloadTask();

        try{
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        }catch(Exception e){
            e.printStackTrace();
        }

        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);

        listView.setAdapter(arrayAdapter);

    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;

            /********* FIRST LAYER OF THE API, THE IDS OF THE ARTICLE *******/
            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                /*********STICK IDS INTO PARSABLE JSON ARRAY*******/
                JSONArray jsonArray = new JSONArray(result);

                int numOfItems = 20;

                //if theres less than 20 items in the array, then we need to set the the number of items to the array size
                if(jsonArray.length() < 20){
                    numOfItems = jsonArray.length();
                }

                for(int i=0; i< numOfItems; i++){
                    String articleId=jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId +".json?print=pretty");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    inputStream = urlConnection.getInputStream();
                    reader = new InputStreamReader(inputStream);
                    data = reader.read();

                    String articleInfo = "";

                    while(data != -1){
                        char current = (char) data;
                        articleInfo += current;
                        data = reader.read();
                    }

                    /*********GET URL AND TITLE*******/
                    JSONObject jsonObj = new JSONObject(articleInfo);
                    if(!jsonObj.isNull("title") && !jsonObj.isNull("url")){
                        String articleTitle = jsonObj.getString("title");
                        String articleUrl = jsonObj.getString("url");
                        Log.i("Title/URL", articleTitle + "\n" + articleUrl);

                        /*********GET CONTENT OF ARTICLE*******/
                        url = new URL(articleUrl);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        inputStream = urlConnection.getInputStream();
                        reader = new InputStreamReader(inputStream);
                        data = reader.read();
                        String articleContent = "";
                        while(data != -1){
                            char current = (char) data;
                            articleContent += current;
                            data = reader.read();
                        }
                        String sql = "INSERT INTO articles(articleId,title,content) VALUES(?,?,?,?)";
                        SQLiteStatement statement = articleDb.compileStatement(sql);
                        statement.bindString(1,articleId);
                        statement.bindString(2,articleTitle);
                        statement.bindString(3,articleContent);
                    }
                }

                Log.i("URL Content", result);
                return result;
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
