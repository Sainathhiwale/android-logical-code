package com.dorvis.contentproviderdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ContentProviderDemo";



    private TextView textViewQueryResult;
    private Button buttonLoadData;


    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};

    private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";

    private String[] mSelectionArguments = new String[]{"Sai"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewQueryResult = (TextView) findViewById(R.id.textViewQueryResult);

        buttonLoadData = (Button) findViewById(R.id.buttonLoadData);

        buttonLoadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,null,null,null);

        if (cursor!=null && cursor.getCount()>0){
            StringBuilder  builder = new StringBuilder(" ");
            while (cursor.moveToFirst()){
                builder.append(cursor.getString(0)+" , "+cursor.getString(1)+" , "+cursor.getString(2)+"\n");
            }
            textViewQueryResult.setText(builder.toString());
        }else {
            textViewQueryResult.setText("not contact here");
        }
    }
}
