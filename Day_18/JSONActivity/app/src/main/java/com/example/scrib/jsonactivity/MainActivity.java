package com.example.scrib.jsonactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    TextView cityView;
    TextView currentTempView;
    TextView highTempView;
    TextView lowTempView;
    TextView humidityView;

    EditText editText;

    ImageView weatherImageView;

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

//            try{
//                JSONObject jsonObj = new JSONObject(s);
//                String weatherInfo = jsonObj.getString("weather");
//                JSONArray arr = new JSONArray(weatherInfo);
//
//                for(int i=0; i< arr.length(); i++){
//                    jsonPart = arr.getJSONObject(i);
//                }
//                weatherView.setText(jsonPart.getString("main")+": " + jsonPart.getString("description"));
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }

            try{
                JSONObject jsonObj = new JSONObject(s);
                JSONObject test = jsonObj.getJSONObject("main");
                JSONObject conditionJSON = getKeyValue("weather", jsonObj);


                String con = conditionJSON.getString("main");
                String cityString = jsonObj.getString("name");
                String temperature = test.getString("temp") + " C°";
                String highTemp = test.getString("temp_max") + " C°";
                String lowTemp = test.getString("temp_min") + " C°";
                String humidity = test.getString("humidity") + "%";

                currentTempView.setText("Current Temperature: " + temperature);
                highTempView.setText("Daily High: " + highTemp);
                lowTempView.setText("Daily Low: " + lowTemp);
                humidityView.setText("Humidity: " + humidity);
                cityView.setText(cityString);
                weatherView.setText(con);

                switch (con){
                    case "Snow":
                        weatherImageView.setImageResource(R.drawable.snow);
                        break;
                    case "Clouds":
                        weatherImageView.setImageResource(R.drawable.clouds);
                        break;
                    case "Clear":
                        weatherImageView.setImageResource(R.drawable.sun);
                        break;
                    case "Rain":
                        weatherImageView.setImageResource(R.drawable.rain);
                        break;
                    default: weatherImageView.setImageResource(R.drawable.question);
                }

            }catch(Exception e){
                e.printStackTrace();
            }


        }
    }

    public JSONObject getKeyValue(String key, JSONObject json){
        try{

            String Info = json.getString(key);
            JSONArray arr = new JSONArray(Info);

            for(int i=0; i< arr.length(); i++){
                jsonPart = arr.getJSONObject(i);
            }
            return jsonPart;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void getWeather(View view){
        String city = editText.getText().toString();
        TaskDownload task = new TaskDownload();
        task.execute("https://openweathermap.org/data/2.5/weather?q="+city+"&appid=b6907d289e10d714a6e88b30761fae22");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherView = findViewById(R.id.weatherView);
        cityView = findViewById(R.id.cityView);
        editText = findViewById(R.id.editText);
        currentTempView = findViewById(R.id.currentTemp);
        lowTempView = findViewById(R.id.lowTemp);
        highTempView = findViewById(R.id.highTemp);
        humidityView = findViewById(R.id.humdity);

        weatherImageView = findViewById(R.id.conditionImage);
//        TaskDownload task = new TaskDownload();
//        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");
    }
}
