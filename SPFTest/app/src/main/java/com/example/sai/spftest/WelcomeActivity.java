package com.example.sai.spftest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tv_name = (TextView)findViewById(R.id.tv_userName);
        TextView tv_password = (TextView)findViewById(R.id.tv_userPassword);

        
    }
}
