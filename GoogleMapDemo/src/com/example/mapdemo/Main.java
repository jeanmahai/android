package com.example.mapdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.mytree.GoogleMapDemo.R;

public class Main extends Activity {

    private final String LOG_TAG = "GOOGLE_MAP";

    private GoogleMap map;
    private FragmentManager fragmentManager;

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
//                configMapOption();
                map.setMyLocationEnabled(true);
                Location location=map.getMyLocation();
                if(location!=null){
                    log(location.toString());
                }
            }
        }
    }

    private void configMapOption() {
        log("配置地图...");
        GoogleMapOptions ops=new GoogleMapOptions();
        ops.compassEnabled(true);
    }

    private void log(String msg) {
        Log.i(LOG_TAG, msg);
    }
}
