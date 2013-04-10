package com.mytree.EasySecretary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Main extends Activity {

    private Button btnSend;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSend = (Button) findViewById(R.id.btnSend);
    }
}