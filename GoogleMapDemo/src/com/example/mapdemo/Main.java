package com.example.mapdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mytree.GoogleMapDemo.R;

public class Main extends Activity implements LocationListener {

    private final String LOG_TAG = "GOOGLE_MAP";

    private GoogleMap map;
    private FragmentManager fragmentManager;
    private LocationManager locationManager;
    private String provider;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fragmentManager = getFragmentManager();

        if (map == null) {
            map = ((MapFragment) fragmentManager.findFragmentById(R.id.map)).getMap();
            if (map == null) {
                log("当前地图不可用");
            } else {
                log("地图已准备就绪");
                Location location= getLocation();
                LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
                Marker current=map.addMarker(new MarkerOptions().position(latlng).title("my position"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
                map.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
            }
        }
    }

    private void configMapOption() {
        log("配置地图...");
        GoogleMapOptions ops = new GoogleMapOptions();
        ops.compassEnabled(true);
    }

    private void log(String msg) {
        Log.i(LOG_TAG, msg);
    }

    private Location getLocation() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            log(provider);
            log(String.format("lat:%s,lgn:%s", location.getLatitude(), location.getLongitude()));
        } else {
            log("location disabled");
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        //To change body of implemented methods use File | Settings | File Templates.
        log(String.format("lat:%s,lgn:%s", location.getLatitude(), location.getLongitude()));
        LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
        Marker current=map.addMarker(new MarkerOptions().position(latlng).title("my position"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
        map.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderEnabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderDisabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
