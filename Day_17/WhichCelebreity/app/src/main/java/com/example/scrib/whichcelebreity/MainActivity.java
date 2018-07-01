package com.example.scrib.whichcelebreity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> celebURLs = new ArrayList<String>();
    public ArrayList<String> celebNames = new ArrayList<String>();
    String[] answers = new String[4];
    int selectedCeleb = 0;
    int correctAnswerLocation = 0;
    int incorrectAnswerLocation;

    ImageView imageView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public class ImageDownload extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(input);
                return bitMap;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public void userSelected(View view){
        if(view.getTag().toString().equals(Integer.toString(correctAnswerLocation))){
            Toast.makeText(getApplicationContext(),"Correct!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Incorrect! It's " + celebNames.get(selectedCeleb), Toast.LENGTH_SHORT).show();
        }
        play();
    }

    public void play(){
        Random r = new Random();
        selectedCeleb = r.nextInt(celebURLs.size());

        try{
        ImageDownload imageTask = new ImageDownload();
        Bitmap celebImage = imageTask.execute(celebURLs.get(selectedCeleb)).get();
        imageView.setImageBitmap(celebImage);

        correctAnswerLocation = r.nextInt(4);

        for(int i=0; i<4; i++){
            if(i == correctAnswerLocation){
                answers[i] = celebNames.get(selectedCeleb);
            }else{

                incorrectAnswerLocation = r.nextInt(celebURLs.size());

                while(incorrectAnswerLocation == selectedCeleb){
                    incorrectAnswerLocation = r.nextInt(celebURLs.size());
                }
                answers[i] = celebNames.get(incorrectAnswerLocation);
            }
        }

        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);



    }catch(Exception e){
        e.printStackTrace();
    }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        DownloadTask task = new DownloadTask();
        String res = null;

        try {
            res = task.execute("http://www.posh24.se/kandisar").get();
            Log.i("URL Content", res);

            String[] splitResult = res.split("<div class=\"listedArticles\">");
            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);


            while (m.find()) {
                Log.i("Output", m.group(1));
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()) {
                Log.i("Output", m.group(1));
                celebNames.add(m.group(1));
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        play();
    }

}
