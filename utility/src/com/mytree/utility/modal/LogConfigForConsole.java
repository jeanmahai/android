package com.mytree.utility.modal;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-29
 * Time: 下午7:36
 * To change this template use File | Settings | File Templates.
 */
public class LogConfigForConsole extends LogConfig {
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;

    public LogConfigForConsole(){
        super("");
        this.tag = "";
    }

    public LogConfigForConsole(String level, String tag) {
        super(level);
        this.tag = tag;
    }
}
