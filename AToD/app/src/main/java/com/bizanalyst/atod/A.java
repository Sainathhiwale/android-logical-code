package com.bizanalyst.atod;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class A extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void activity_A(View view) {

        Intent intentA = new Intent();
        intentA.setComponent(new ComponentName(getApplicationContext(),B.class));
        startActivity(intentA);
    }
}
