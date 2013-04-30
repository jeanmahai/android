package com.mytree.GoogleMapDemo;

import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mytree.utility.L;
import com.mytree.utility.SafeActivity;

public class Main extends SafeActivity {

    private GoogleMap map;
    private FragmentManager fragmentManager;
    private LocationManager locationManager;
    private String provider;
    private int counter=0;

    private LocationListener locationListener;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        L.setupConfig(this);
        L.i("onCreate");

        fragmentManager = getFragmentManager();

        if (map == null) {
            map = ((MapFragment) fragmentManager.findFragmentById(R.id.map)).getMap();
            if (map == null) {
                L.i("地图不存在");
            } else {
                L.i("地图已准备就绪");
                setMark(initLocation());
            }
        }
    }

    private Location initLocation(){
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                L.i("onLocationChanged");
                setMark(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                L.i("onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                L.i("onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                L.i("onProviderDisabled");
            }
        };

        locationManager.requestLocationUpdates(provider, 100, 100, locationListener);

        //first load location
        return locationManager.getLastKnownLocation(provider);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(locationManager!=null){
            locationManager.removeUpdates(locationListener);
        }
    }

    private void setMark(Location location){
        L.i(String.format("维度:%s,经度:%s",location.getLatitude(),location.getLongitude()));
        if(map!=null){
            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions().position(latlng).title(String.format("%s",counter)));
            counter++;
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
        }
    }
}
