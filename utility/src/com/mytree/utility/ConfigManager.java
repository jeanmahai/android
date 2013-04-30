package com.mytree.utility;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.mytree.utility.modal.LogConfig;
import com.mytree.utility.modal.LogConfigForConsole;
import com.mytree.utility.modal.LogConfigForFile;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-28
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class ConfigManager {
    public static List<LogConfig> getLogConfig(Context context) throws XmlPullParserException, IOException {
        XmlResourceParser parser = context.getResources().getXml(R.xml.logconfig);
        int type = parser.getEventType();
        List<LogConfig> result = new ArrayList<LogConfig>();
        LogConfigForFile file = null;
        LogConfigForConsole console = null;
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    String val;
                    if (parser.getName().equalsIgnoreCase("file")) {
                        val = parser.getAttributeValue(null, "enable");
                        if (val.equalsIgnoreCase("true"))
                            file = new LogConfigForFile();
                    }
                    if (parser.getName().equalsIgnoreCase("console")) {
                        val = parser.getAttributeValue(null, "enable");
                        if (val.equalsIgnoreCase("true"))
                            console = new LogConfigForConsole();
                    }
                    /////////////////////////////////////////////////////////////////
                    if (parser.getName().equalsIgnoreCase("dir")) {
                        val = parser.getAttributeValue(null, "val");
                        if (file != null)
                            file.setDir(val);
                    }
                    if (parser.getName().equalsIgnoreCase("filename")) {
                        val = parser.getAttributeValue(null, "val");
                        if (file != null)
                            file.setFileName(val);
                    }
                    if (parser.getName().equalsIgnoreCase("level")) {
                        val = parser.getAttributeValue(null, "val");
                        if (file != null)
                            file.setLevelText(val);
                        if (console != null) {
                            console.setLevelText(val);
                        }
                    }
                    if (parser.getName().equalsIgnoreCase("tag")) {
                        val = parser.getAttributeValue(null, "val");
                        if (console != null) {
                            console.setTag(val);
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equalsIgnoreCase("file")) {
                        result.add(file);
                        file = null;
                    }
                    if (parser.getName().equalsIgnoreCase("console")) {
                        result.add(console);
                        console = null;
                    }
                    break;
            }

            type = parser.next();
        }
        return result;
    }
}

