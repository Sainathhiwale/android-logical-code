package com.bcil.arraylist.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcil.arraylist.NumberActivity;
import com.bcil.arraylist.utils.AppConstant;

public class NumberListener implements View.OnClickListener {
   private Context context;

    public NumberListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent numberIntent = new Intent(context, NumberActivity.class);
        numberIntent.putExtra("Data", AppConstant.NAME);
        context.startActivity(numberIntent);

    }
}
