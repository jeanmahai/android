package com.mytree.utility;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
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
public class Location {

    public static void getLocation() {

    }

    private static LocationManager locationManager;
    private static String provider;
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

    /*判断手机是否支持GPS*/
    private static boolean hasGPS(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }

    /*GPS是否开启*/
    private static boolean isActivityGPS(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        }
        return false;
    }

    /*根据GPS获取地理位置信息*/
    private static void getLocationByGPS(Context context) {
        if (hasGPS(context)) {
            if (isActivityGPS(context)) {

            } else {
                Toast.makeText(context, "GPS服务未开启", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "你的手机不支持GPS", Toast.LENGTH_SHORT).show();
        }
    }

    /*根据网络获取地理位置信息*/
    private static void getLocationByNet(Context context) {
        NetworkInfo network = Connection.getConnection(context);
        if (network == null) {
            Toast.makeText(context, "当前没有可用的网络", Toast.LENGTH_SHORT).show();
        }

    }

    public static void getBaseStation(final Context context, final Handler success) throws JSONException, IOException {
        BaseStation bs = new BaseStation();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
        if (location == null) {
            return;
        }
        String operator = tm.getNetworkOperator();
//        bs.mcc = Integer.parseInt(operator.substring(0, 3));
//        bs.mnc = Integer.parseInt(operator.substring(3));
//        bs.id = location.getCid();
//        bs.lac = location.getLac();
        final int mcc = Integer.parseInt(operator.substring(0, 3));
        final int mnc = Integer.parseInt(operator.substring(3));
        final int id = location.getCid();
        final int lac = location.getLac();


/** 发出POST数据并获取返回数据 */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    //获取经纬度
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost("http://www.google.com/loc/json");

                    JSONObject data = new JSONObject();
                    data.put("version", "1.1.0");
                    data.put("host", "maps.google.com");
                    data.put("address_language", "zh_CN");
                    data.put("request_address", "true");
                    data.put("radio_type", "gsm");
                    //data.put("version", "1.1.0");

                    JSONObject tower = new JSONObject();
                    tower.put("mobile_country_code", mcc);
                    tower.put("mobile_network_code", mnc);
                    tower.put("cell_id", id);
                    tower.put("location_area_code", lac);


                    JSONArray towerarray = new JSONArray();
                    towerarray.put(tower);

                    data.put("cell_towers", towerarray);

                    StringEntity query = new StringEntity(data.toString());
                    post.setEntity(query);
                    HttpResponse response = client.execute(post);
                    HttpEntity entity = response.getEntity();
                    BufferedReader buffReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    StringBuffer strBuff = new StringBuffer();
                    String result = null;
                    while ((result = buffReader.readLine()) != null) {
                        strBuff.append(result);
                    }

                    /** 解析返回的JSON数据获得经纬度 */
                    JSONObject json = new JSONObject(strBuff.toString());
                    JSONObject subjosn = new JSONObject(json.getString("location"));

                    Message msg = new Message();
                    Bundle data2 = new Bundle();
                    data2.putString("json", subjosn.toString());
                    msg.setData(data2);
                    success.sendMessage(msg);
                } catch (IOException e) {
                    Message msg = new Message();
                    Bundle data2 = new Bundle();
                    data2.putString("json", e.getMessage());
                    msg.setData(data2);
                    success.sendMessage(msg);
                } catch (JSONException e) {
                    Message msg = new Message();
                    Bundle data2 = new Bundle();
                    data2.putString("json", e.getMessage());
                    msg.setData(data2);
                    success.sendMessage(msg);
                } catch (Exception e) {
                    Message msg = new Message();
                    Bundle data2 = new Bundle();
                    data2.putString("json", e.getMessage());
                    msg.setData(data2);
                    success.sendMessage(msg);
                }
            }
        };
        new Thread(runnable).start();
//        HttpResponse response = client.execute(post);
//        HttpEntity entity = response.getEntity();
//        BufferedReader buffReader = new BufferedReader(new InputStreamReader(entity.getContent()));
//        StringBuffer strBuff = new StringBuffer();
//        String result = null;
//        while ((result = buffReader.readLine()) != null) {
//            strBuff.append(result);
//        }
//
//        /** 解析返回的JSON数据获得经纬度 */
//        JSONObject json = new JSONObject(strBuff.toString());
//        JSONObject subjosn = new JSONObject(json.getString("location"));

//        itude.latitude = subjosn.getString("latitude");
//        itude.longitude = subjosn.getString("longitude");

//        Log.i("Itude", itude.latitude + itude.longitude);
//        bs.x=subjosn.getString("latitude");
//        bs.y=subjosn.getString("longitude");
//        return bs;
    }


}

