package com.company.scribner.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

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

    LocationManager locationManager;
    LocationListener locationListener;

    private String addressString;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());
                try{
                    List<Address> addressList = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addressList != null && addressList.size() > 0){
                        addressString = "";
                        if(addressList.get(0).getSubThoroughfare() != null){
                            addressString += addressList.get(0).getSubThoroughfare() + " ";
                        }

                        //Thoroughfare == road?
                        if(addressList.get(0).getThoroughfare() != null){
                            addressString += addressList.get(0).getThoroughfare() + "\n";
                        }

                        //Locality == city
                        if(addressList.get(0).getLocality() != null){
                            addressString += addressList.get(0).getLocality() + ", ";
                        }

                        //AdminArea == state
                        if(addressList.get(0).getAdminArea() != null){
                            addressString += addressList.get(0).getAdminArea() + " ";
                        }

                        //Locality == zip
                        if(addressList.get(0).getPostalCode() != null){
                            addressString += "," + addressList.get(0).getPostalCode();
                        }
                    }else{
                        addressString = "No Address Near By";
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }


                latitude.setText("Latitude: " + String.format("%.2f", location.getLatitude()));
                longitude.setText("Longitude: " + String.format("%.2f", location.getLongitude()));
                altitude.setText("Altitude: " + String.valueOf(location.getAltitude()));
                accuracy.setText("Accuracy: " + String.valueOf(location.getAccuracy()));
                address.setText("Address: \n" + addressString);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }

    }
}
