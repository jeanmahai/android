package com.mytree.utility;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-28
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class SafeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        L.i("create global exception handler");
        GlobalExceptionHandler exHandler=GlobalExceptionHandler.getInstance();
        exHandler.init(this);
    }
}
