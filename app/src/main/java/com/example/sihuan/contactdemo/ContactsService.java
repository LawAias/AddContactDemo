package com.example.sihuan.contactdemo;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;

import java.util.ArrayList;


/**
 * ContactsService
 * Created by SiHuan on 2017/4/6.
 */

public class ContactsService {

    private Context context;

    public ContactsService(Context context) {
        this.context = context;
    }

    public void addContact(ContactPerson cp) throws RemoteException, OperationApplicationException {
        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();
        operations.add(op1);

        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA2, cp.getName())
                .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA1, cp.getPhone())
                .withValue(ContactsContract.CommonDataKinds.Email.DATA2, "2")
                .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA1, cp.getEmail())
                .withValue(ContactsContract.CommonDataKinds.Email.DATA2, "2")
                .build();
        operations.add(op4);

        resolver.applyBatch(ContactsContract.AUTHORITY, operations);
    }


    public void  addSms(SmsInfo smsInfo) throws RemoteException, OperationApplicationException {
        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        ContentProviderOperation op = ContentProviderOperation.newInsert(uri)
                .withValue("address",smsInfo.getAddress())
                .withValue("body",smsInfo.getBody())
                .withValue("date",smsInfo.getDate())
                .withValue("type",smsInfo.getType())
                .withValue("service_center",smsInfo.getService_center())
                .withValue("read",smsInfo.getRead())
                .build();
        operations.add(op);

        resolver.applyBatch("sms", operations);


    }


    public void insertCallLog(CallLogInfo callLogInfo) throws RemoteException, OperationApplicationException {


        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        ContentProviderOperation op = ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI)
                .withValue(CallLog.Calls.NUMBER, callLogInfo.getNumber())
                .withValue(CallLog.Calls.DATE, callLogInfo.getDate())
                .withValue(CallLog.Calls.DURATION, callLogInfo.getDuration())
                .withValue(CallLog.Calls.TYPE, callLogInfo.getType())
                .withValue(CallLog.Calls.NEW, callLogInfo.getUnRead())
                .build();
        operations.add(op);

        resolver.applyBatch(CallLog.AUTHORITY, operations);

//        ContentValues values = new ContentValues();
//        values.put(CallLog.Calls.NUMBER, string);
//        values.put(CallLog.Calls.DATE, System.currentTimeMillis()+i);
//        values.put(CallLog.Calls.DURATION, string2);
//        values.put(CallLog.Calls.TYPE,string3);//未接
//        values.put(CallLog.Calls.NEW, string4);//0已看1未看
//
//        context.getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
    }


}
