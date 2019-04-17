package com.bcil.arraylist.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcil.arraylist.ContactActivity;

public class ContactListener implements View.OnClickListener {
    private Context context;

    public ContactListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent contactIntent = new Intent(context, ContactActivity.class);
        context.startActivity(contactIntent);
    }
}
