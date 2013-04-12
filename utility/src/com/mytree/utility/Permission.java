package com.mytree.utility;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: jm96
 * Date: 13-4-12
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class Permission {
    public static boolean hasPermission(Context context, String permission){
        PackageManager pm=context.getPackageManager();
        String packageName=context.getPackageName();
        if(pm.checkPermission(permission,packageName)==PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    public static boolean hasPermission(Context context,String permission,boolean notify){
        boolean has= hasPermission(context, permission);
        if(has==false && notify==true){
            Toast.makeText(context,String.format("please config permission:%n%s",permission),Toast.LENGTH_LONG).show();
        }
        return has;
    }
}
