package com.example.sihuan.contactdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * SmsActivity
 * Created by SiHuan on 2017/4/7.
 */

public class SmsActivity extends Activity {

    private String packeageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // to be the default sms app
        Intent intent1 = getIntent();
        if (intent1 != null) {
            packeageName = intent1.getStringExtra("packeage_name");
            if (Build.VERSION.SDK_INT > 18) {
                Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packeageName);
                startActivityForResult(intent, 0);

            }
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("sihuan", "activity result " + resultCode);
        switch (resultCode) {
            case -1:
                SmsService.smsSuccess = true;
                break;
            case 0:
                SmsService.smsSuccess = false;
                break;
        }
        SmsService.b = false;
        synchronized (SmsService.class) {
            SmsService.class.notify();
        }
        finish();
    }


    //    String defaultSmsApp = null;
//        if (Build.VERSION.SDK_INT > 18) {
//        Log.d("FoneGo", "to be the default sms app");
//        // After 4.4.2 kitkat, we need make ourself as the default sms app
//        defaultSmsApp = Telephony.Sms.getDefaultSmsPackage(this);
//        if (defaultSmsApp.equals(getPackageName())) {
//            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
//            startActivityForResult(intent, 0);
//            // we need listen to the sms arrival message?
//        }
//    }
}
