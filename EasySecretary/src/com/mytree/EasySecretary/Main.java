package com.mytree.EasySecretary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mytree.utility.Connection;
import com.mytree.utility.SMS;

public class Main extends Activity {

    private Button btnSend;
    private Button btnGetConnection;
    private TextView txtConnection;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendMessage();
            }
        });

        btnGetConnection = (Button) findViewById(R.id.btnGetConnection);
        btnGetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                //SecurityManager security=new SecurityManager();
                //if(checkPermission())
//                PackageManager pm=getPackageManager();
//                if(pm.checkPermission("android.permission.ACCESS_NETWORK_STATE","com.mytree.EasySecretary")==PackageManager.PERMISSION_GRANTED){
//                    txtConnection.setText("当前程序包含网络状态的权限");
//                }
//                else{
//                    txtConnection.setText("当前程序不包含网络状态的权限");
//                }
//                try {
                NetworkInfo netWork = Connection.getConnection(v.getContext());
                if (netWork == null) {
                    txtConnection.setText("当前没有任何网络连接");
                } else {
                    txtConnection.setText(netWork.getTypeName());
                }
//                } catch (Exception ex) {
//                    txtConnection.setText(ex.getMessage());
//                }
            }
        });

        txtConnection = (TextView) findViewById(R.id.txtConnectionState);
    }

    private void sendMessage() {
        String phoneNo = ((EditText) findViewById(R.id.txtPhoneNumber)).getText().toString().trim();
        String message = ((EditText) findViewById(R.id.txtMessage)).getText().toString().trim();
        if (SMS.isPhoneNumber(phoneNo) && !SMS.isEmptyMessage(message)) {
            SMS.send(this, phoneNo, message);
        }

    }
}