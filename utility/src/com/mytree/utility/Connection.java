package com.mytree.utility;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-12
 * Time: 下午2:01
 * 网络顺序,WIFI,3G
 * To change this template use File | Settings | File Templates.
 */
public class Connection {
    /*获取一个可用的连接,如果没有任何连接,则返回null*/
    public static NetworkInfo getConnection(Context context) {
        if (Permission.hasPermission(context, Manifest.permission.ACCESS_NETWORK_STATE, true)) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return manager.getActiveNetworkInfo();
        }
        return null;
    }
}
