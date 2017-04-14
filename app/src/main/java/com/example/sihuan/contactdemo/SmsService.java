package com.example.sihuan.contactdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * SmsService
 * Created by SiHuan on 2017/4/7.
 */

public class SmsService extends Service {
    public static Context mContext;
    public static Boolean b;
    public static Boolean smsSuccess = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        b = true;
    }


}
