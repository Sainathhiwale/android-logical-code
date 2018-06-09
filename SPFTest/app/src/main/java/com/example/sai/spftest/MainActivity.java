package com.example.sai.spftest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);

    }

    public void login(View view) {

        EditText et_User = (EditText)findViewById(R.id.et_name);
        EditText et_Pass = (EditText)findViewById(R.id.et_password);

        //read password and user name from SharedPreferencesdatabase

        SharedPreferences spf = getSharedPreferences("myspf", Context.MODE_PRIVATE);

        String User = spf.getString("name","no value");
        String Pass = spf.getString("password","no value");


        if (et_User.getText().toString().equalsIgnoreCase(User)&& et_Pass.getText().toString().equalsIgnoreCase(Pass)){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getApplicationContext(),WelcomeActivity.class));
            startActivity(intent);
        }
    }
}
