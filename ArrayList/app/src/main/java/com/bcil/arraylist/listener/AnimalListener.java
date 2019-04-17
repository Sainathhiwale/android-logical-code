package com.bcil.arraylist.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcil.arraylist.AnimalActivity;

public class AnimalListener implements View.OnClickListener {
    private Context context;

    public AnimalListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent animalIntent = new Intent(context, AnimalActivity.class);
        context.startActivity(animalIntent);
    }
}
