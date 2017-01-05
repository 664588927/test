package com.gogenius.learningdemos.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

/**
 * Created by shijiwei on 2016/9/20.
 */
public class ContactsActivity extends Activity {

    private final int REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getContacts() {

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    ContentResolver contactsResolver = getContentResolver();
                    Uri contactsUri = data.getData();
//                    Cursor cursor =
                    break;
            }
        }
    }
}
