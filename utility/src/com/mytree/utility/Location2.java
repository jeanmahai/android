package com.mytree.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;
import com.mytree.utility.modal.BaseStation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-15
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */
public class Location2 {

    private static LocationManager locationManager;
    private static PackageManager packageManager;
    private static Context ctx;
    private static String provider;

//    private static LocationListener gpsListener;
//    private static GpsStatus.Listener gpsStatusListener;

    public static void init(Context context) {
        if (ctx == null) {
            ctx = context;
        }
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        if (packageManager == null) {
            packageManager = context.getPackageManager();
        }
    }

    /**
     * ************************************************************************
     */
    /*
    GPS:
    */
    public static void initGps(LocationListener gpsListener, GpsStatus.Listener gpsStatusListener) {
        //检查手机是否支持GPS
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
            L.i("当前手机不支持GPS模块");
            return;
        }
        //检查GPS是否开启
        if (!locationManager.isProviderEnabled("gps")) {
            L.i("当前手机的GPS没有开启");
            //TODO 跳转到GPS设置页面
            new AlertDialog
                    .Builder(ctx)
                    .setTitle("提示信息")
                    .setMessage("是否现在去设置?")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ctx.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                        }
                    })
                    .setNegativeButton("否", null)
                    .show();

            return;
        }

        locationManager.addTestProvider(LocationManager.GPS_PROVIDER, false, true, false, false, true, true, true, Criteria.POWER_LOW, 10);

//        locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
        if (gpsListener != null) {
            locationManager.addGpsStatusListener(gpsStatusListener);
            L.i("添加gps状态监听成功");
        }

        if (gpsListener != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 10, gpsListener);
            L.i(String.format("GPS 开始监听"));
        }
    }
    /****************************************************************************/
    /*基站*/
    /****************************************************************************/
    /*网络*/
    /****************************************************************************/
    /*
    *
    * */

    /**
     * ************************************************************************
     */

    public static android.location.Location getMyLocation(Context context) {
        if (locationManager == null)
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (provider == null) {
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, true);
        }
        if (provider != null) {
            return locationManager.getLastKnownLocation(provider);
        } else {
            L.i("provider is null");
            return null;
        }
    }
}

