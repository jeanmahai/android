package com.mytree.EasySecretary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mytree.utility.Connection;
import com.mytree.utility.Location;
import com.mytree.utility.SMS;
import com.mytree.utility.modal.BaseStation;

public class Main extends Activity {

    private Button btnSend;
    private Button btnGetConnection;
    private Button btnGetStation;
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

        txtConnection = (TextView) findViewById(R.id.txtConnectionState);
        btnGetConnection = (Button) findViewById(R.id.btnGetConnection);
        btnGetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkInfo netWork = Connection.getConnection(v.getContext());
                if (netWork == null) {
                    txtConnection.setText("当前没有任何网络连接");
                } else {
                    txtConnection.setText(netWork.getTypeName());
                }
            }
        });


        btnGetStation = (Button) findViewById(R.id.btnGetBaseStation);
        btnGetStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    final Context ctx=v.getContext();
                    Location.getBaseStation(v.getContext(), new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            Bundle data=msg.getData();
                            String json=data.getString("json");
                            Log.i("tag","执行完成"+json);
                        }
                    });
                } catch (Exception ex) {
                    Log.i("tag",ex.getMessage());
                }
            }
        });
    }

    private void sendMessage() {
        String phoneNo = ((EditText) findViewById(R.id.txtPhoneNumber)).getText().toString().trim();
        String message = ((EditText) findViewById(R.id.txtMessage)).getText().toString().trim();
        if (SMS.isPhoneNumber(phoneNo) && !SMS.isEmptyMessage(message)) {
            SMS.send(this, phoneNo, message);
        }
    }
}