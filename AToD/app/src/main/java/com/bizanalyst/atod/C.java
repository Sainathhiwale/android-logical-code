package com.bizanalyst.atod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class C extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }

    public void activity_C(View view) {
        Intent intentC = new Intent(C.this,D.class);
        startActivity(intentC);
    }
}
