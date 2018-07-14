package com.bizanalyst.atod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class B extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }

    public void activity_B(View view) {
        Intent intentB = new Intent(B.this,C.class);
        startActivity(intentB);
    }
}
