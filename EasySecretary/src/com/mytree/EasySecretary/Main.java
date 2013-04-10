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
//                new AlertDialog.Builder(view.getContext()).setMessage("send clicked").show();
            }
        });
    }

    private void sendMessage(){
        String phoneNo=((EditText)findViewById(R.id.txtPhoneNumber)).getText().toString().trim();
        String message=((EditText)findViewById(R.id.txtMessage)).getText().toString().trim();

        StringBuffer buffer=new StringBuffer();
        buffer.append(String.format("Phone:%s%n",phoneNo));
        buffer.append(String.format("Message:%s",message));

//        new AlertDialog.Builder(this).setMessage(buffer.toString()).show();

        SmsManager sms=SmsManager.getDefault();
        PendingIntent intent=PendingIntent.getActivity(this,0,new Intent(),0);
        sms.sendTextMessage(phoneNo,null,message,intent,null);
        Toast.makeText(Main.this,"短信发送成功",Toast.LENGTH_LONG).show();
    }
}