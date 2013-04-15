package com.mytree.utility.modal;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-11
 * Time: 下午9:04
 * To change this template use File | Settings | File Templates.
 */
public class Result {
    public boolean success;
    public int code;
    public Object data;
    public String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Result(boolean success, Object data) {
        this(success);
        this.data = data;
    }

}
