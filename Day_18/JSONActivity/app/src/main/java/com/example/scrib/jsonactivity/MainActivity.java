package com.example.scrib.jsonactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    TextView weatherView;

    JSONObject jsonPart;

    public class TaskDownload extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONObject jsonObj = new JSONObject(s);
                String weatherInfo = jsonObj.getString("weather");
                JSONArray arr = new JSONArray(weatherInfo);

                for(int i=0; i< arr.length(); i++){
                    jsonPart = arr.getJSONObject(i);
                }
                weatherView.setText(jsonPart.getString("main")+": " + jsonPart.getString("description"));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void getWeather(View view){
        TaskDownload task = new TaskDownload();
        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherView = findViewById(R.id.weatherView);

//        TaskDownload task = new TaskDownload();
//        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");
    }
}
