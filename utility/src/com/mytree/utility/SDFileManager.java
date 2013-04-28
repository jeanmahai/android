package com.mytree.utility;

import android.os.Environment;
import org.apache.http.util.EncodingUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-28
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class SDFileManager {

    /*获得SD卡的根目录*/
    public File getBasePath() {
        return Environment.getExternalStorageDirectory();
    }

    /*判断SD卡是否可用*/
    public boolean availableSD(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /*向文件中写数据*/
    public void write(File file, String data)
            throws IOException {
        FileOutputStream stream = null;
        stream = new FileOutputStream(file);
        stream.write(data.getBytes());
        stream.close();
    }
    /*向文件中写数据,如果文件或者文件夹不存在将自动创建*/
    public void write2(File file, String data)
            throws IOException {
        File dir = new File(file.getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        write(file, data);
    }

    /*从文件中读数据*/
    public String read(File file) throws Exception {
        if(!file.exists()){
            throw new Exception(String.format("文件:%s 不存在",file.getAbsoluteFile()));
        }
        FileInputStream stream=new FileInputStream(file);
        byte[] buffer=new byte[stream.available()];
        stream.read(buffer);
        String result= EncodingUtils.getString(buffer,"utf-8");
        return result;
    }

    public String read(File file, String encode) {
        return "";
    }
}
