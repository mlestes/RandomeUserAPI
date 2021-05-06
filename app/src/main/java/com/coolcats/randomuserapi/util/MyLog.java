package com.coolcats.randomuserapi.util;

import android.util.Log;

public class MyLog {

    private static String TAG = "MY_TAG";

    public static void logDebug(String message){
        Log.d(TAG, message);
    }

    public static void logError(String message){
        Log.e(TAG, message);
    }

    public static void logInfo(String message){
        Log.i(TAG, message);
    }

    public static void logWarn(String message){
        Log.w(TAG, message);
    }

}
