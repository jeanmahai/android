package com.mytree.utility;

import android.content.Context;
import android.util.Log;
import com.mytree.utility.modal.LogConfig;
import com.mytree.utility.modal.LogConfigForConsole;
import com.mytree.utility.modal.LogConfigForFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-26
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class L {
    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;

    public static final String TXT_VERBOSE = "VERBOSE";
    public static final String TXT_DEBUG = "DEBUG";
    public static final String TXT_INFO = "INFO";
    public static final String TXT_WARN = "WARN";
    public static final String TXT_ERROR = "ERROR";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_SHORT = new SimpleDateFormat("yyyy-MM-dd");

    private static LogConfigForConsole console = null;
    private static List<LogConfigForFile> files = new ArrayList<LogConfigForFile>();

    private static List<LogConfig> configs = null;

    public static void setupConfig(Context context) {
        if (configs == null) {
            try {
                configs = ConfigManager.getLogConfig(context);
            } catch (XmlPullParserException e) {
                return;
            } catch (IOException e) {
                return;
            }
        }
        if(files==null){
            files=new ArrayList<LogConfigForFile>();
        }
        else{
            files.clear();
        }
        for (LogConfig c : configs) {
            if (c instanceof LogConfigForFile) {
                files.add((LogConfigForFile) c);
            }
            if (c instanceof LogConfigForConsole) {
                console = (LogConfigForConsole) c;
            }
        }
    }

    //info
    public static void i(String msg) {
        msg=formatMessage(msg,TXT_INFO);
        if (console != null && console.getLevel() <= INFO) {
            Log.i(console.getTag(), msg);
        }
        for (LogConfigForFile file : files) {
            if (file.getLevel() <= INFO) {
                try {
                    writeLog(file, msg);
                } catch (IOException e) {
                }
            }
        }
    }

    //error
    public static void e(String msg) {
        msg=formatMessage(msg,TXT_ERROR);
        if (console != null && console.getLevel() <= ERROR) {
            Log.e(console.getTag(), msg);
        }
        for (LogConfigForFile file : files) {
            if (file.getLevel() <= ERROR) {
                try {
                    writeLog(file, msg);
                } catch (Exception ex) {

                }
            }
        }
    }

    //verbose
    public static void v(String msg) {
        msg=formatMessage(msg,TXT_VERBOSE);
        if (console != null && console.getLevel() <= VERBOSE) {
            Log.v(console.getTag(), msg);
        }
        for (LogConfigForFile file : files) {
            if (file.getLevel() <= VERBOSE) {
                try {
                    writeLog(file, msg);
                } catch (IOException e) {
                }
            }
        }
    }

    //debug
    public static void d(String msg) {
        msg=formatMessage(msg,TXT_DEBUG);
        if (console != null && console.getLevel() <= DEBUG) {
            Log.d(console.getTag(), msg);
        }
        for (LogConfigForFile file : files) {
            if (file.getLevel() <= DEBUG) {
                try {
                    writeLog(file, msg);
                } catch (IOException e) {
                }
            }
        }
    }

    //warn
    public static void w(String msg) {
        msg=formatMessage(msg,TXT_WARN);
        if (console != null && console.getLevel() <= WARN) {
            Log.w(console.getTag(), msg);
        }
        for (LogConfigForFile file : files) {
            if (file.getLevel() <= WARN) {
                try {
                    writeLog(file, msg);
                } catch (IOException e) {
                }
            }
        }
    }

    private static String formatMessage(String msg, String level) {
        Date currentDate = new Date();
        return String.format("%s\t%s\t%s\n", DATE_FORMAT.format(currentDate), level, msg);
    }

    private static void writeLog(LogConfigForFile file, String msg) throws IOException {
        File dir = new File(file.getFullDir());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath=file.getFullFilePath();
        if(filePath.contains("{date}")){
            filePath=filePath.replace("{date}",DATE_FORMAT_SHORT.format(new Date()));
        }
        File logFile = new File(filePath);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileOutputStream stream = new FileOutputStream(logFile,true);
        stream.write(msg.getBytes());
        stream.close();
    }
}
