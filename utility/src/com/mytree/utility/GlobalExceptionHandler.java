package com.mytree.utility;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-28
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private Context context;

    private static GlobalExceptionHandler instance = new GlobalExceptionHandler();

    private GlobalExceptionHandler() {
    }

    public static GlobalExceptionHandler getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        this.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        //To change body of implemented methods use File | Settings | File Templates.
        final String message = ex.getMessage();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //crash log
        L.v(message);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
