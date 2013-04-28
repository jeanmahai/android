package com.example.utility_pro;

import android.os.Bundle;
import com.mytree.utility.L;
import com.mytree.utility.SDFileManager;
import com.mytree.utility.SafeActivity;

import java.io.File;

public class MyActivity extends SafeActivity {

    private String s;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        L.i("done");
        SDFileManager file=new SDFileManager();
        L.i(String.format("SD是否可用:%s", file.availableSD()));

        L.i(String.format("%s",s.equals("s")));
    }
}
