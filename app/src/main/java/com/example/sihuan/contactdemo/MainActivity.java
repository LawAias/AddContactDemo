package com.example.sihuan.contactdemo;

import android.Manifest;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_CALL_LOG};
    private static final int REQUEST_CONTACTS = 1;


    private EditText name;
    private EditText num;
    private Button btn;
    private Button addSms;

    private ContactPerson cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        checkPermission();
        final ContactsService cs = new ContactsService(this);
        addSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                    cs.addSms(new SmsInfo("你好", "20170407", 0, "123456798", 1, "932168545"));
                try {
                    cs.insertCallLog(new CallLogInfo("123456789", System.currentTimeMillis() + 1000, "10000", "2", "1"));
                } catch (RemoteException | OperationApplicationException e) {
                    e.printStackTrace();
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp = new ContactPerson(name.getText().toString(), num.getText().toString(), "sh932168545@gmail.com");
                try {
                    cs.addContact(cp);
                    Log.e("sihuan", "addContact");
                } catch (RemoteException | OperationApplicationException e) {
                    e.printStackTrace();
                    Log.e("sihuan", e.toString());
                }
            }
        });

    }

    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        num = (EditText) findViewById(R.id.num);
        btn = (Button) findViewById(R.id.btn);
        addSms = (Button) findViewById(R.id.add_sms);

    }


    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED
                    || (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED)
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG)
                    != PackageManager.PERMISSION_DENIED) {
                requestContactsPermissions();
            }
        }
    }


    private void requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALL_LOG)) {
            Log.d("sihuan", "11");
        } else {

            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACTS:
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


}
