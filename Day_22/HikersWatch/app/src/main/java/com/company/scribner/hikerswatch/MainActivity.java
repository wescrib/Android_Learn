package com.company.scribner.hikerswatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.Window.FEATURE_NO_TITLE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)TextView title;
    @BindView(R.id.latitudeView)TextView latitude;
    @BindView(R.id.longitudeView)TextView longitude;
    @BindView(R.id.altitudeView)TextView altitude;
    @BindView(R.id.accuracyView)TextView accuracy;
    @BindView(R.id.addressView)TextView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);







    }
}
