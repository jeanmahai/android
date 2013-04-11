package com.mytree.utility;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Jeanma
 * Date: 13-4-11
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */
public  class SMS {
    public static boolean send(Context context, String phoneNumber,String message){
        Result result=send(context,phoneNumber, message,null);
        return result.success;
    }

    public static Result send(Context context,
                            String phoneNumber,
                            String message,
                            CallbackInterface achieved){

        Result result=new Result(false);

        if(!isPhoneNumber(phoneNumber)){
            result.message="非法的电话号码";
            return result;
        }

        message=message.trim();
        if(message.isEmpty()){
            result.message="短信内容不能为空";
            return result;
        }
        SmsManager sms=SmsManager.getDefault();

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,new Intent(),0);

        if(message.length()>70){
            //直接发送单个短信
            sms.sendTextMessage(phoneNumber,null,message,null,null);
        }
        else{
            //发送多个短信
            ArrayList<String> multipleMessage=sms.divideMessage(message);
            sms.sendMultipartTextMessage(phoneNumber,null,multipleMessage,null,null);
        }
//        if(success!=null){
//            success.callback();
//        }
        result.success=true;
        result.message="短信发送成功";
        return result;
    }

    private static boolean isPhoneNumber(String phoneNumber){
        Pattern pattern=Pattern.compile("^1[0-9]{10}$");
        Matcher matcher=pattern.matcher(phoneNumber);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
