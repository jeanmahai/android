package com.mytree.utility;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-26
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class L {
    private static final String TAG="UTILITY_LOG_TAG";

    public static void i(String msg){
        Log.i(TAG,msg);
    }

    public static void e(String msg){
        Log.e(TAG,msg);
    }

    public static void v(String msg){
        Log.v(TAG,msg);
    }

    public static void d(String msg){
        Log.d(TAG,msg);
    }

    public static void w(String msg){
        Log.w(TAG,msg);
    }
}
