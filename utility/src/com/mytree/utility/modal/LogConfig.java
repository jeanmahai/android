package com.mytree.utility.modal;

import com.mytree.utility.L;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-29
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
public class LogConfig {

    private String levelText;

    public int getLevel() {
        return level;
    }

    private int level;

    public void setLevelText(String val) {
        this.levelText = val;
        if (val.equalsIgnoreCase(L.TXT_VERBOSE)) {
            this.level=L.VERBOSE;
        } else if (val.equalsIgnoreCase(L.TXT_DEBUG)) {
            this.level=L.DEBUG;
        } else if (val.equalsIgnoreCase(L.TXT_INFO)) {
            this.level=L.INFO;
        } else if (val.equalsIgnoreCase(L.TXT_WARN)) {
            this.level=L.WARN;
        } else if (val.equalsIgnoreCase(L.TXT_ERROR)) {
            this.level=L.ERROR;
        }
    }

    public String getLevelText() {
        return this.levelText;
    }

    public LogConfig(String level) {
        this.levelText = level;
    }
}

