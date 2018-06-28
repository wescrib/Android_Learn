package com.company.scribner.imagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage(View view){

        ImageDownLoader task = new ImageDownLoader();
        Bitmap myImage;

        try{
            myImage = task.execute("https://i.pinimg.com/originals/fc/28/b0/fc28b0290f2f17b0f9f08e4babbe5dd6.jpg").get();
            imageView.setImageBitmap(myImage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public class ImageDownLoader extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
