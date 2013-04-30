package com.mytree.GoogleMapDemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mytree.utility.ConfigManager;
import com.mytree.utility.L;
import com.mytree.utility.SafeActivity;
import com.mytree.utility.modal.LogConfig;
import com.mytree.utility.modal.LogConfigForFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main extends SafeActivity implements LocationListener {

    private final String LOG_TAG = "GOOGLE_MAP";

    private GoogleMap map;
    private FragmentManager fragmentManager;
    private LocationManager locationManager;
    private String provider;

//
//    private Button btn;
//    private EditText txt;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        L.setupConfig(this);
        L.i("onCreate");
//        txt=(EditText)findViewById(R.id.editText);
//        btn=(Button)findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                L.v(txt.getText().toString());
//            }
//        });

//        fragmentManager = getFragmentManager();
//
//        if (map == null) {
//            map = ((MapFragment) fragmentManager.findFragmentById(R.id.map)).getMap();
//            if (map == null) {
//                log("当前地图不可用");
//            } else {
//                log("地图已准备就绪");
//                Location location = getLocation();
//                LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
//                Marker current = map.addMarker(new MarkerOptions().position(latlng).title("my position"));
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
//                map.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
//
//            }
//        }
    }

    private void configMapOption() {
        log("配置地图...");
        GoogleMapOptions ops = new GoogleMapOptions();
        ops.compassEnabled(true);
    }

    private void log(String msg) {
        L.i(msg);
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
        L.i("onLocationChanged");
        //To change body of implemented methods use File | Settings | File Templates.
//        log(String.format("lat:%s,lgn:%s", location.getLatitude(), location.getLongitude()));
//        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
        L.i(String.format("lat:%s,lgn:%s", location.getLatitude(), location.getLongitude()));
//        Marker current = map.addMarker(new MarkerOptions().position(latlng).title("my position"));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
//        map.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
        L.i("onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
        L.i("onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
        L.i("onProviderDisabled");
    }
}
