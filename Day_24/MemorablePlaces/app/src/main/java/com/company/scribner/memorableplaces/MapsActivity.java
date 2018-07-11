package com.company.scribner.memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    public void centerMapOnLoc(Location loc, String title){
        if(loc != null) {
            LatLng userLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLoc).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 10));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLoc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapOnLoc(lastKnownLoc, "You are here");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);

        Intent intent = getIntent();

        //if they click the first item of the listview...
        if(intent.getIntExtra("placeNum", 0) == 0){
            //scale in location
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //centerMapOnLoc(location, "You are here");
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


            //if they have access, then get their location, if not, ask for permission
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLoc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapOnLoc(lastKnownLoc, "You are here");
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }else{
            Location placeLocation = new Location (LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(MainActivity.coords.get(intent.getIntExtra("placeNum",0)).latitude);
            placeLocation.setLongitude(MainActivity.coords.get(intent.getIntExtra("placeNum", 0)).longitude);
            centerMapOnLoc(placeLocation, MainActivity.locs.get(intent.getIntExtra("placeNum", 0)));
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());

        String address = "";

        try{
            List<Address> addressList = gc.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if(addressList != null && addressList.size() > 0){
                if(addressList.get(0).getThoroughfare() != null){
                    if(addressList.get(0).getSubThoroughfare() != null){
                        address += addressList.get(0).getSubThoroughfare();
                    }
                    address += addressList.get(0).getThoroughfare();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        if(address.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd | HH:mm");
            address += sdf.format(new Date());
        }

        MainActivity.locs.add(address);
        MainActivity.coords.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
    }
}
