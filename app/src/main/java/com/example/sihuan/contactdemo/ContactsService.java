package com.example.sihuan.contactdemo;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
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


}
