package com.mytree.EasySecretary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mytree.utility.SMS;

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
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String phoneNo = ((EditText) findViewById(R.id.txtPhoneNumber)).getText().toString().trim();
        String message = ((EditText) findViewById(R.id.txtMessage)).getText().toString().trim();
        if(SMS.isPhoneNumber(phoneNo)&& !SMS.isEmptyMessage(message)){
            SMS.send(this, phoneNo, message);
        }

    }
}