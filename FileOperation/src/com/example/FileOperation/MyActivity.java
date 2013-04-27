package com.example.FileOperation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyActivity extends Activity {
    private final String LOG_PATH = "/logs";
    private Button btnWrite;
    private EditText txtMessage;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnWrite = (Button) findViewById(R.id.btnWrite);
        txtMessage = (EditText) findViewById(R.id.txtMessage);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    writeMessage(txtMessage.getText().toString());
                    writeMessage2(txtMessage.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

//        checkLogDir();
    }

    //check日志文件夹是否存在,如果不存在则需要创建
    //data/logs
    private void checkLogDir() {
        File root = this.getFilesDir();
        log(String.format("根目录为:%s", root.getPath()));
        File logDir = new File(root.getPath() + LOG_PATH);
        if (!logDir.exists()) {
            log(String.format("目录%s不存在,开始创建...", logDir.getPath()));
            logDir.mkdirs();
            log(String.format("目录%s创建成功", logDir.getPath()));
        } else {
            log(String.format("目录%s已经存在", logDir.getPath()));
        }
    }

    private void writeMessage(String message) throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        File currentLog = new File(this.getFilesDir() + "/logs/" + format.format(currentDate) + ".log");
        if (!currentLog.exists()) {
            currentLog.createNewFile();
        }
        if (!currentLog.canWrite()) {
            currentLog.setWritable(true, true);
        }
        FileOutputStream stream = null;
        try {
            stream = this.openFileOutput(currentLog.getName(), Context.MODE_APPEND);
//            stream=new FileInputStream(currentLog);
            byte[] bytes = message.getBytes();
            stream.write(bytes);
            stream.close();
            log(String.format("写日志成功:%s", message));
        } catch (Exception ex) {
            if (stream != null) {
                stream.close();
            }
            log(String.format("写日志失败:%s", message));
        }
    }

    private void log(String msg) {
        Log.i("TAG", msg);
    }

    private void writeMessage2(String message) throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FileOperation/logs";// + format.format(currentDate) + ".log";
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        path+="/"+format.format(currentDate) + ".log";
        File file=new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream stream = null;
        try {
            stream=new FileOutputStream(file,true);
            message+="<br/>";
            stream.write(message.getBytes());
            stream.close();
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            if (stream != null) {
                stream.close();
            }
            Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
        }
    }
}
