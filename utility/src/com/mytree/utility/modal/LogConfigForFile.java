package com.mytree.utility.modal;

import android.os.Environment;

public class LogConfigForFile extends LogConfig {
    private String dir;

    public void setDir(String val) {
        this.dir = val;
    }

    public String getDir() {
        return this.dir;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullDir() {
        return Environment.getExternalStorageDirectory() +"/"+ this.getDir();
    }

    public String getFullFilePath() {
        return this.getFullDir() +"/"+ this.getFileName();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public LogConfigForFile() {
        super("");
        this.dir = "";
        this.fileName = "";
    }

    public LogConfigForFile(String level, String dir, String fileName) {
        super(level);
        this.dir = dir;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return String.format("level:%s,dir:%s,fileName:%s", this.getLevelText(), this.getDir(), this.getFileName());
    }
}
